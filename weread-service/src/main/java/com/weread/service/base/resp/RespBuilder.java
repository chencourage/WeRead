package com.weread.service.base.resp;

import java.util.Collections;
import java.util.List;

import com.weread.service.base.req.BaseMultiReq;

/**
 * 响应类构造器
 * @author lisheng
 * @date 2019年1月15日
 */
public class RespBuilder {
	
	/** 构建CommonResp */
	public static CommonResp getSuccessCommonResp() {
		return new CommonResp(CommonResp.SUCCESS_RESP_CODE, CommonResp.SUCCESS_RESP_DESC);
	}
	
	public static CommonResp getSuccessCommonResp(String respDesc) {
		return new CommonResp(CommonResp.SUCCESS_RESP_CODE, respDesc);
	}
	
	public static CommonResp getFailCommonResp(String respDesc) {
		return new CommonResp(CommonResp.FAIL_RESP_CODE, respDesc);
	}
	
	public static CommonResp getFailCommonResp(String respCode, String respDesc) {
		return new CommonResp(respCode, respDesc);
	}
	
	/** 构建SingleResp */
	public static <T> SingleResp<T> getSuccessSingleResp(T data) {
		return new SingleResp<>(CommonResp.SUCCESS_RESP_CODE, CommonResp.SUCCESS_RESP_DESC, data);
	}
	
	public static <T> SingleResp<T> getSuccessSingleResp(String respDesc, T data) {
		return new SingleResp<T>(CommonResp.SUCCESS_RESP_CODE, respDesc, data);
	}
	
	public static <T> SingleResp<T> getSingleResp(CommonResp commonResp) {
		return new SingleResp<T>(commonResp.getRespCode(), commonResp.getRespDesc(), null);
	}
	
	public static <T> SingleResp<T> getFailSingleResp(String respDesc) {
		return new SingleResp<>(CommonResp.FAIL_RESP_CODE, respDesc, null);
	}
	
	public static <T> SingleResp<T> getFailSingleResp(String respCode, String respDesc) {
		return new SingleResp<>(respCode, respDesc, null);
	}
	
	/** 构建MultiResp */
	public static <T> MultiResp<T> getSuccessMultiResp(BaseMultiReq req, long total, List<T> rows) {
		return new MultiResp<>(CommonResp.SUCCESS_RESP_CODE, CommonResp.SUCCESS_RESP_DESC, req.getCurrentPage(), req.getPageSize(), total, rows);
	}
	
	public static <T> MultiResp<T> getSuccessMultiResp(int currentPage, int pageSize, long total, List<T> rows) {
		return new MultiResp<>(CommonResp.SUCCESS_RESP_CODE, CommonResp.SUCCESS_RESP_DESC, currentPage, pageSize, total, rows);
	}
	
	public static <T> MultiResp<T> getSuccessMultiResp(String respDesc, int currentPage, int pageSize, long total, List<T> rows) {
		return new MultiResp<>(CommonResp.SUCCESS_RESP_CODE, respDesc, currentPage, pageSize, total, rows);
	}
	
	public static <T> MultiResp<T> getMultiResp(CommonResp commonResp, BaseMultiReq req) {
		if(null == req) {
			return new MultiResp<>(commonResp.getRespCode(), commonResp.getRespDesc(), 1, BaseMultiReq.DEF_PAGE_SIZE, 0, Collections.emptyList()); 
		}
		return new MultiResp<>(commonResp.getRespCode(), commonResp.getRespDesc(), req.getCurrentPage(), req.getPageSize(), 0, Collections.emptyList());
	}
	
	public static <T> MultiResp<T> getFailMultiResp(String respDesc) {
		return new MultiResp<>(CommonResp.FAIL_RESP_CODE, respDesc, 1, BaseMultiReq.DEF_PAGE_SIZE, 0, null);
	}
	
	public static <T> MultiResp<T> getFailMultiResp(String respCode, String respDesc) {
		return new MultiResp<>(respCode, respDesc, 1, BaseMultiReq.DEF_PAGE_SIZE, 0, null);
	}
	
	/** 构建SumMultiResp */
	public static <T1, T2> SumMultiResp<T1, T2> getSuccessSumMultiResp(BaseMultiReq req, long total, List<T1> rows, T2 summary) {
		return new SumMultiResp<>(CommonResp.SUCCESS_RESP_CODE, CommonResp.SUCCESS_RESP_DESC, req.getCurrentPage(), req.getPageSize(), total, rows, summary);
	}
	
	public static <T1, T2> SumMultiResp<T1, T2> getSuccessSumMultiResp(int currentPage, int pageSize, long total, List<T1> rows, T2 summary) {
		return new SumMultiResp<>(CommonResp.SUCCESS_RESP_CODE, CommonResp.SUCCESS_RESP_DESC, currentPage, pageSize, total, rows, summary);
	}
	
	public static <T1, T2> SumMultiResp<T1, T2> getSuccessSumMultiResp(String respDesc, int currentPage, int pageSize, long total, List<T1> rows, T2 summary) {
		return new SumMultiResp<>(CommonResp.SUCCESS_RESP_CODE, respDesc, currentPage, pageSize, total, rows, summary);
	}
	
	public static <T1, T2> SumMultiResp<T1, T2> getSumMultiResp(CommonResp commonResp, BaseMultiReq req, T2 summary) {
		if(null == req) {
			return new SumMultiResp<>(commonResp.getRespCode(), commonResp.getRespDesc(), 1, BaseMultiReq.DEF_PAGE_SIZE, 0, Collections.emptyList(), summary); 
		}
		return new SumMultiResp<>(commonResp.getRespCode(), commonResp.getRespDesc(), req.getCurrentPage(), req.getPageSize(), 0, Collections.emptyList(), summary);
	}
	
	public static <T1, T2> SumMultiResp<T1, T2> getFailSumMultiResp(String respDesc) {
		return new SumMultiResp<>(CommonResp.FAIL_RESP_CODE, respDesc, 1, BaseMultiReq.DEF_PAGE_SIZE, 0, null, null);
	}
	
	public static <T1, T2> SumMultiResp<T1, T2> getFailSumMultiResp(String respCode, String respDesc) {
		return new SumMultiResp<>(respCode, respDesc, 1, BaseMultiReq.DEF_PAGE_SIZE, 0, null, null);
	}
}
