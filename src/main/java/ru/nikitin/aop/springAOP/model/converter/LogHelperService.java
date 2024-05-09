package ru.nikitin.aop.springAOP.model.converter;

import org.aspectj.lang.Signature;
import org.springframework.stereotype.Service;
import ru.nikitin.aop.springAOP.model.LogRow;
import ru.nikitin.aop.springAOP.model.TypeExecution;
import ru.nikitin.aop.springAOP.model.to.LogTO;

import java.util.Date;

@Service
public class LogHelperService {

    public LogTO convert(LogRow logRow) {

        return new LogTO(
                logRow.getId(),
                logRow.getStart(),
                logRow.getEnd(),
                logRow.getExecutionTime(),
                logRow.getType().getTypeName(),
                logRow.getClazz(),
                logRow.getMethod());
    }

    public LogRow convert(LogTO logTO) {
        TypeExecution type = TypeExecution.getType(logTO.type());
        return new LogRow(
                logTO.start(),
                logTO.end(),
                logTO.execute(),
                type,
                logTO.className(),
                logTO.methodName());
    }


    public Long calculateExecute(Date start, Date end) {
        return end.getTime() - start.getTime();
    }

    public String getMethodName(Signature signature) {
        String className = getClassName(signature).concat(".");
        return signature.toString().replace(className, "");
    }

    public String getClassName(Signature signature) {
        return signature.getDeclaringTypeName();

    }

}
