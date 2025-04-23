/**
* @packageName	: 중소기업기술마켓 정식플랫폼 구축
* @fileName		: AttachFileMapper.java
* @author		: kkang
* @date			: 2025.04.22
* @description	:
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.22        kkang       최초 생성
*/
package egovframework.example.cmmn.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.vo.AttachFileVo;

@Mapper
public interface AttachFileMapper {
    /**
     * @methodName   : insertFile
     * @author       : kkang
     * @desc         : 파일 등록
     * @date         : 2025.04.21
     * @param param
     */
     void insertAttachFile(AttachFileVo fileVo);

     /**
    * @methodName	: selectFileList
    * @author		: kkang
    * @desc			: 파일 리스트 불러오기
    * @date			: 2025.04.22
    * @param id
    * @return
    */
    List<AttachFileVo> selectFileList(int id);

    /**
    * @methodName	: upDateAttachFile
    * @author		: kkang
    * @desc			: 첨부파일 업데이트(논리삭제)
    * @date			: 2025.04.23
    * @param fileVo
    */
    void upDateAttachFile(AttachFileVo fileVo);
}
