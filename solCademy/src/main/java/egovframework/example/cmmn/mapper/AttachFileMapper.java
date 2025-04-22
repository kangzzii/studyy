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
}
