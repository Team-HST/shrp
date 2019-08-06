package com.hst.shrp.dao;

import com.hst.shrp.model.entity.EntityCommonCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Repository
public interface AnalysisDAO {
    /**
     * 지표 종류 조회
     *
     * @return
     */
    public List<EntityCommonCode> selectSimulationTypeList();

    /**
     * Ampm 시간대 종류 조회
     *
     * @return
     */
    public List<EntityCommonCode> selectAmpmTypeList();
}

