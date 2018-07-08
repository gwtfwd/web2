package egovframework.example.parkType.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.park.service.ParkSearchVO;
import egovframework.example.park.service.ParkVO;
import egovframework.example.parkType.service.ParkTypeService;
import egovframework.example.parkType.service.ParkTypeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("parkTypeService")

public class ParkTypeServiceImpl extends EgovAbstractServiceImpl implements ParkTypeService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkTypeServiceImpl.class);
	
		/** ParkTypeDAO */
		// TODO ibatis 사용
		@Resource(name = "parkTypeDAO")
		
		private ParkTypeDAO parkTypeDAO;
		// TODO mybatis 사용
		//  @Resource(name="sampleMapper")
		//	private SampleMapper sampleDAO;
		
		/** ID Generation */
		@Resource(name = "parkTypeCodeGnrService")
		private EgovIdGnrService parkTypeCodeGnrService;
		
		
		/**
		 * 글 목록 조회
		 * @param parkSearchVO - 조회할 정보가 담긴 VO
		 * @return 글 목록
		 * @throws Exception
		 */
		public List<?> selectParkTypeList(ParkSearchVO parkSearchVO) throws Exception {
			return parkTypeDAO.selectParkTypeList(parkSearchVO);
		}
		
		/**
		 * 글 총 개수 조회
		 * @param parkSearchVO - 조회할 정보가 담긴 VO
		 * @return 글 총 개수
		 */
		@Override
		public int selectParkTypeListTotCnt(ParkSearchVO parkSearchVO){
			return parkTypeDAO.selectParkTypeListTotCnt(parkSearchVO);
		}
		
		
		/**
		 * 등록
		 * @param vo - 등록할 정보가 담긴 ParkTypeVO
		 * @return 등록 결과
		 * @exception Exception
		 */
		@Override
		public String insertParkType(ParkTypeVO vo) throws Exception {
			LOGGER.debug(vo.toString());

			//** ID Generation Service *//*
			String code = parkTypeCodeGnrService.getNextStringId();
			vo.setCode(code);
			LOGGER.debug(vo.toString());

			parkTypeDAO.insertParkType(vo);
			return code;
		}
		
		/**
		 * 글 조회
		 * @param vo - 조회할 정보가 담긴 ParkTypeVO
		 * @return 조회한 글
		 * @exception Exception
		 */
		@Override
		public ParkTypeVO selectParkType(ParkTypeVO vo) throws Exception {
			ParkTypeVO resultVO = parkTypeDAO.selectParkType(vo);
			if (resultVO == null)
				throw processException("info.nodata.msg");
			return resultVO;
		}
		
		/**
		 * 수정
		 * @param vo - 수정할 정보가 담긴 ParkTypeVO
		 * @return void형
		 * @exception Exception
		 */
		@Override
		public void updateParkType(ParkTypeVO vo) throws Exception {
			parkTypeDAO.updateParkType(vo);
		}
		
		/**
		 * 삭제
		 * @param vo - 삭제할 정보가 담긴 ParkTypeVO
		 * @return void형
		 * @exception Exception
		 */
		@Override
		public void deleteParkType(ParkTypeVO vo) throws Exception {
			parkTypeDAO.deleteParkType(vo);
		}

		
}




