/**
* @packageName  : 중소기업기술마켓 정식플랫폼 구축
* @fileName     : CategoryServiceImpl.java
* @author       : kkang
* @date         : 2025.04.01
* @description  :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2025.04.01        kkang       최초 생성
*/
package egovframework.example.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.bbs.mapper.CategoryMapper;
import egovframework.example.bbs.service.CategoryService;
import egovframework.example.bbs.vo.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    
    @Autowired
    CategoryMapper categoryMapper;
    
    /**
    * @methodName	: getList
    * @author		: kkang
    * @desc			: 리스트 조회
    * @date			: 2025.04.01
    * @return
    */
    @Override
    public List<?> getList(CategoryVo categoryVo){
        return categoryMapper.selectList(categoryVo);
    }
    

    /**
    * @methodName   : getDetail
    * @author       : kkang
    * @desc         : 상세 조회
    * @date         : 2025.04.01
    * @return
    */
    @Override
    public CategoryVo getDetail(int id) {
        return categoryMapper.selectDetail(id);
    }

    /**
    * @methodName   : modifyDetail
    * @author       : kkang
    * @desc         : 수정
    * @date         : 2025.04.01
    * @return
    */
    @Override
    public void modifyDetail(CategoryVo categoryVo) {
        categoryMapper.updateDetail(categoryVo);
    }

    /**
    * @methodName   : deleteDetail
    * @author       : kkang
    * @desc         : 삭제
    * @date         : 2025.04.01
    * @return
    */
    @Override
    public void deleteDetail(int id) {
        categoryMapper.deleteDetail(id);
    }
    
    /**
    * @methodName   : registerDetail
    * @author       : kkang
    * @desc         : 등록
    * @date         : 2025.04.01
    * @return
    */
    @Override
    public void registerDetail(String Category_nm) {
        categoryMapper.inserDetail(Category_nm);
    }
}
