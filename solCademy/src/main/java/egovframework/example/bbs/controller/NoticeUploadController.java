/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: NoticeUploadController.java
* @author		: kkang
* @date			: 2025.04.15
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.15        kkang       최초 생성
*/
package egovframework.example.bbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.zip.InflaterInputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NoticeUploadController {

    Logger log = LoggerFactory.getLogger(getClass());
    private String hostName = "//192.168.35.217";
    /**
    * @methodName	: uploadFile
    * @author		: kkang
    * @desc			: 이미지, 동영상, 파일 업로드
    * @date			: 2025.04.16
    * @param request
    * @param file
    * @return
    * @throws IOException
    */
    static String IMAGE_UPLOAD_DIR_REL_PATH = "uploads";
    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file)
            throws IOException {
//        String ROOT_ABS_PATH = request.getSession().getServletContext().getRealPath("");
        String UPLOAD_DIR_ABS_PATH = hostName + "/upload/editor/images/20250416/";

        makeDirectory(UPLOAD_DIR_ABS_PATH);

        String fileName = file.getOriginalFilename();
        String ext = "";
        String contentType = file.getContentType();
        if(contentType != null) {
            ext = "." + contentType.substring(contentType.lastIndexOf('/') + 1);
        } else if (fileName.lastIndexOf('.') > 0) {
            ext = fileName.substring(fileName.lastIndexOf('.'));
        }
        if (ext.indexOf(".jpeg") > -1) { // jpg가 더많이쓰여서 jpeg는 jpg로 변환
            ext = ".jpg";
        }
        String saveFileName = UUID.randomUUID().toString() + ext;
        String saveFileAbsPath = UPLOAD_DIR_ABS_PATH + File.separator + saveFileName;

        writeFile(saveFileAbsPath, file.getBytes());

        Map<String, Object> map = new HashMap<String, Object>();

        // 브라우저에서 접근가능한 경로를 uploadPath에 담아서 넘겨줍니다.
        map.put("uploadPath", hostName + ":8085/upload/editor/images/20250416/" + saveFileName);

        return map;
    }


    /**
    * @methodName	: importDoc
    * @author		: kkang
    * @desc			: HWP, MS 워드, 엑셀 문서 임포트
    * @date			: 2025.04.16
    * @param request
    * @param importFile
    * @return
    * @throws IOException
    */
    static String DOC_UPLOAD_DIR_REL_PATH = "uploads" + File.separator + "docs";
    static String OUTPUT_DIR_REL_PATH = "uploads" + File.separator + "output";

    @RequestMapping(value = "/importDoc.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importDoc(HttpServletRequest request, @RequestParam("file") MultipartFile importFile)
            throws IOException {
        String ROOT_ABS_PATH = request.getSession().getServletContext().getRealPath("");
        String UPLOAD_DIR_ABS_PATH =  hostName + "/upload/editor/docs/20250416/";

        makeDirectory(UPLOAD_DIR_ABS_PATH);

        String fileName = importFile.getOriginalFilename();
        String inputFileAbsPath = UPLOAD_DIR_ABS_PATH + File.separator + fileName;

        writeFile(inputFileAbsPath, importFile.getBytes());

        // 파일별로 변환결과를 저장할 경로 생성
        Calendar cal = Calendar.getInstance();
        String yearMonth = String.format("%04d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
        String uuid = UUID.randomUUID().toString();
        String worksDirAbsPath = ROOT_ABS_PATH + File.separator + OUTPUT_DIR_REL_PATH + File.separator + yearMonth + File.separator + uuid;

        makeDirectory(worksDirAbsPath);

        // 문서 변환
        executeConverter(inputFileAbsPath, worksDirAbsPath, ROOT_ABS_PATH);

        // 변환이 끝난 원본파일은 삭제한다.
        deleteFile(inputFileAbsPath);

        // 변환된 pb파일을 읽어서 serialzie
        // v2.3.0 부터 파일명이 document.word.pb에서 document.pb로 변경됨
        String pbAbsPath = worksDirAbsPath + File.separator + "document.pb";
        Integer[] serializedData = serializePbData(pbAbsPath);

        // pb파일은 삭제
        // v2.3.0 부터 파일명이 document.word.pb에서 document.pb로 변경됨
        deleteFile(pbAbsPath);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("serializedData", serializedData);
        // 브라우저에서 접근가능한 경로를 importPath에 담아서 넘겨줍니다.
        // OUTPUT_DIR_REL_PATH 경로에 맞춰서 수정해야 합니다.
        map.put("importPath", "uploads/output/" + yearMonth + "/" + uuid);

        return map;
    }

    /**
     * 파일을 씁니다.
     */
        private static void writeFile(String path, byte[] bytes) throws IOException {
            OutputStream os = null;
            try {
                os = new FileOutputStream(path);
                os.write(bytes);
            } finally {
                if (os != null) os.close();
            }
        }

    /**
     * 디렉토리가 없는 경우 디렉토리를 생성합니다.
     */
    private static void makeDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * 문서 변환 모듈을 실행합니다.
     */
    public static int executeConverter(String inputFilePath, String outputFilePath, String ROOT_ABS_PATH) {
        String SEDOC_CONVERTER_DIR_ABS_PATH = "D:\\file\\SynapEditorPackage\\sedocConverter\\windows";
        String FONT_DIR_ABS_PATH = SEDOC_CONVERTER_DIR_ABS_PATH + File.separator + "fonts";
        String TEMP_DIR_ABS_PATH = SEDOC_CONVERTER_DIR_ABS_PATH + File.separator + "temp";
        String SEDOC_CONVERTER_ABS_PATH = SEDOC_CONVERTER_DIR_ABS_PATH + File.separator + "sedocConverter.exe";// window server용
        // String SEDOC_CONVERTER_ABS_PATH = SEDOC_CONVERTER_DIR_ABS_PATH + File.separator + "sedocConverter.exe";// linux server용

        makeDirectory(TEMP_DIR_ABS_PATH);
        makeDirectory(FONT_DIR_ABS_PATH);

        // 변화 명령 구성
        String[] cmd = {SEDOC_CONVERTER_ABS_PATH, "-f", FONT_DIR_ABS_PATH, inputFilePath, outputFilePath, TEMP_DIR_ABS_PATH};
        try {
            Timer t = new Timer();

             // JDK 1.7 이상
            ProcessBuilder builder = new ProcessBuilder(cmd);
            // 프로세스의 출력과 에러 스트림을 상속하도록 설정 (부모 프로세스의 콘솔로 출력됨)
            builder.redirectOutput(Redirect.INHERIT);
            builder.redirectError(Redirect.INHERIT);

            Process proc = builder.start();

            // JDK 1.6 이하
            /*
            Process proc = Runtime.getRuntime().exec(cmd);
            // 표준 출력 처리
            try (InputStream procInput = proc.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = procInput.read(buffer)) != -1) {
                    System.out.write(buffer, 0, bytesRead);
                }
            }
            // 에러 스트림 처리
            try (InputStream procError = proc.getErrorStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = procError.read(buffer)) != -1) {
                    System.err.write(buffer, 0, bytesRead);
                }
            }
            */

            TimerTask killer = new TimeoutProcessKiller(proc);
            t.schedule(killer, 20000); // 20초 (변환이 20초 안에 완료되지 않으면 프로세스 종료)

            int exitValue = proc.waitFor();
            killer.cancel();

            return exitValue;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 문서 모듈 실행 후 변환된 결과를 Serialize 합니다.
     */
    public static Integer[] serializePbData(String pbFilePath) throws IOException {
        List<Integer> serializedData = new ArrayList<Integer>();
        FileInputStream fis = null;
        InflaterInputStream ifis = null;
        Integer[] data = null;

        try {
            fis = new FileInputStream(pbFilePath);
            fis.skip(16);

            ifis = new InflaterInputStream(fis);
            byte[] buffer = new byte[1024];

            int len;
            while ((len = ifis.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    serializedData.add(buffer[i] & 0xFF);
                }
            }

            data = serializedData.toArray(new Integer[serializedData.size()]);
        } finally {
            if (ifis != null) ifis.close();
            if (fis != null) fis.close();
        }

        return data;
    }

    /**
     * 파일을 삭제합니다.
     */
    private static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private static class TimeoutProcessKiller extends TimerTask {
        private Process p;

        public TimeoutProcessKiller(Process p) {
            this.p = p;
        }

        @Override
        public void run() {
            p.destroy();
        }
    }
}
