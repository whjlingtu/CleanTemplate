package com.project.whj.clearntemplate.network.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wanghongjun on 2018/7/10.
 */

public class ActCommon {

    /**
     * total : 0
     * rows : [{"id":153060728654256,"actType":"6","applyUser":"黄灶光","nextAppId":25,"applyTime":1530607286000},{"id":153084358448002,"actType":"8","applyUser":"李亮","nextAppId":25,"applyTime":1530843551000},{"id":153084358449543,"actType":"8","applyUser":"李亮","nextAppId":25,"applyTime":1530843551000},{"id":153084553686027,"actType":"8","applyUser":"李亮","nextAppId":25,"applyTime":1530845520000},{"id":153085647123944,"actType":"4","applyUser":"李亮","nextAppId":25,"applyTime":1530856446000},{"id":153118691843778,"actType":"5","applyUser":"wh","nextAppId":25,"applyTime":1531186918000}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * id : 153060728654256
         * actType : 6
         * applyUser : 黄灶光
         * nextAppId : 25
         * applyTime : 1530607286000
         */

        private long id;
        private String actType;
        private String applyUser;
        private int nextAppId;
        private long applyTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getActType() {
            return actType;
        }

        public void setActType(String actType) {
            this.actType = actType;
        }

        public String getApplyUser() {
            return applyUser;
        }

        public void setApplyUser(String applyUser) {
            this.applyUser = applyUser;
        }

        public int getNextAppId() {
            return nextAppId;
        }

        public void setNextAppId(int nextAppId) {
            this.nextAppId = nextAppId;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }
    }
}
