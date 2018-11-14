package com.ssm.dto.base.log;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zcjlq
 */
public class OperLog implements Serializable {
    private static final long serialVersionUID = -4929870809031511460L;
    private Integer tid;

    // 操作用户id
    private Integer operUserTid;

    // 操作用户
    private String operUser;

    // 操作人ip
    private String ip;

    // 模块名称
    private String moduleName;

    // 操作类型
    private String operType;

    // 日志所属Controller
    private String controller;

    // Controller中方法名称
    private String method;

    // 备注
    private String remark;

    // 日志类型 1 系统自动记录日志 2 手工记录日志
    private String logType;

    // 操作开始时间
    private Date startTime;

    // 操作结束时间
    private Date endTime;

    // 耗时，单位s
    private String useTime;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getOperUserTid() {
        return operUserTid;
    }

    public void setOperUserTid(Integer operUserTid) {
        this.operUserTid = operUserTid;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser == null ? null : operUser.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller == null ? null : controller.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime == null ? null : useTime.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OperLog other = (OperLog) that;
        return (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
                && (this.getOperUserTid() == null ? other.getOperUserTid() == null : this.getOperUserTid().equals(other.getOperUserTid()))
                && (this.getOperUser() == null ? other.getOperUser() == null : this.getOperUser().equals(other.getOperUser()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
                && (this.getOperType() == null ? other.getOperType() == null : this.getOperType().equals(other.getOperType()))
                && (this.getController() == null ? other.getController() == null : this.getController().equals(other.getController()))
                && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getLogType() == null ? other.getLogType() == null : this.getLogType().equals(other.getLogType()))
                && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getUseTime() == null ? other.getUseTime() == null : this.getUseTime().equals(other.getUseTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getOperUserTid() == null) ? 0 : getOperUserTid().hashCode());
        result = prime * result + ((getOperUser() == null) ? 0 : getOperUser().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getOperType() == null) ? 0 : getOperType().hashCode());
        result = prime * result + ((getController() == null) ? 0 : getController().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getLogType() == null) ? 0 : getLogType().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getUseTime() == null) ? 0 : getUseTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", operUserTid=").append(operUserTid);
        sb.append(", operUser=").append(operUser);
        sb.append(", ip=").append(ip);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", operType=").append(operType);
        sb.append(", controller=").append(controller);
        sb.append(", method=").append(method);
        sb.append(", remark=").append(remark);
        sb.append(", logType=").append(logType);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", useTime=").append(useTime);
        sb.append("]");
        return sb.toString();
    }
}