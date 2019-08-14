package com.hst.shrp.service;

import com.hst.shrp.dao.AnalysisDAO;
import com.hst.shrp.model.entity.EntityCommonCode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dudgns0612@gmail.com
 */
@Service
public class AnalysisService {

    private final AnalysisDAO analysisDAO;

    public AnalysisService(AnalysisDAO analysisDAO) {
        this.analysisDAO = analysisDAO;
    }
}
