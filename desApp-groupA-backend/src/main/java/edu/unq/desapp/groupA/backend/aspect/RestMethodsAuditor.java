package edu.unq.desapp.groupA.backend.aspect;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class RestMethodsAuditor {

	private static Logger LOGGER = Logger.getLogger("edu.unq.desapp.groupA.backend.webservice.audited");

	@Before("execution(* edu.unq.desapp.groupA.backend.webservice..*(..))")
    public void auditRestMethod(JoinPoint jp) throws Throwable {
		String target = jp.getTarget().getClass().getName();
		String invokingMethodName = jp.getSignature().getName();
		Object[] signatureArgs = jp.getArgs();
		String args = buildStringArgs(signatureArgs);
		String toLog = target + "." + invokingMethodName + "(" + args + ")";
		LOGGER.info(toLog);
    }
	
	private String buildStringArgs(Object[] args) {
		if (args.length == 0) {
			return "";
		} else {
			List<String> stringArgs = new LinkedList<String>();
			for (Object signatureArg: args) {
				stringArgs.add(signatureArg.toString());
			}
			return "Parameters: ".concat(String.join(", ", stringArgs));
		}
	}

}
