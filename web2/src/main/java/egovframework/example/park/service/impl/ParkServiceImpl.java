package egovframework.example.park.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.park.service.ParkSearchVO;
import egovframework.example.park.service.ParkService;
import egovframework.example.park.service.ParkVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service("parkService")

public class ParkServiceImpl extends EgovAbstractServiceImpl implements ParkService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkServiceImpl.class);
	
	/** ParkDAO */
	// TODO ibatis 사용
	@Resource(name = "parkDAO")
	
	private ParkDAO parkDAO;
	// TODO mybatis 사용
	//  @Resource(name="sampleMapper")
	//	private SampleMapper sampleDAO;
	
	/** ID Generation */
	@Resource(name = "parkIdGnrService")
	private EgovIdGnrService parkIdGnrService;
	
	
	/**
	 * 글 목록 조회
	 * @param parkSearchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @throws Exception
	 */
	public List<?> selectParkList(ParkSearchVO parkSearchVO) throws Exception {
		return parkDAO.selectParkList(parkSearchVO);
	}
	
	/**
	 * 글 총 개수 조회
	 * @param parkSearchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 */
	@Override
	public int selectParkListTotCnt(ParkSearchVO parkSearchVO){
		return parkDAO.selectParkListTotCnt(parkSearchVO);
	}
	
	/**
	 * 등록
	 * @param vo - 등록할 정보가 담긴 ParkVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	@Override
	public String insertPark(ParkVO vo) throws Exception {
		LOGGER.debug(vo.toString());

		//** ID Generation Service *//*
		/*Integer Id = parkIdGnrService.getNextStringId();
		vo.setId(id);
		LOGGER.debug(vo.toString());*/

		return parkDAO.insertPark(vo);
	}
	
	/**
	 * 글 조회
	 * @param vo - 조회할 정보가 담긴 ParkVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	@Override
	public ParkVO selectPark(ParkVO vo) throws Exception {
		ParkVO resultVO = parkDAO.selectPark(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 수정
	 * @param vo - 수정할 정보가 담긴 ParkVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void updatePark(ParkVO vo) throws Exception {
		parkDAO.updatePark(vo);
	}

	/**
	 * 삭제
	 * @param vo - 삭제할 정보가 담긴 ParkVO
	 * @return void형
	 * @exception Exception
	 */
	@Override
	public void deletePark(ParkVO vo) throws Exception {
		parkDAO.deletePark(vo);
	}

}
