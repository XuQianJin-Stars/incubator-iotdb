package cn.edu.thu.tsfiledb.qp.physical;

import java.util.List;
import cn.edu.thu.tsfile.timeseries.read.qp.Path;
import cn.edu.thu.tsfiledb.qp.logical.Operator.OperatorType;

/**
 * This class is a abstract class for all type of PhysicalPlan.
 * 
 * @author kangrong
 * @author qiaojialin
 */
public abstract class PhysicalPlan {
    private boolean isQuery;
    private OperatorType operatorType;

    protected PhysicalPlan(boolean isQuery, OperatorType operatorType) {
        this.isQuery = isQuery;
        this.operatorType = operatorType;
    }

    public String printQueryPlan() {
        return "abstract plan";
    }

    public abstract List<Path> getPaths();

    public boolean isQuery(){
        return isQuery;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

}