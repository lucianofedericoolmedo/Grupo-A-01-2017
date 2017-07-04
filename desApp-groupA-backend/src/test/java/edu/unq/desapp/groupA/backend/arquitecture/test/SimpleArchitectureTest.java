package edu.unq.desapp.groupA.backend.arquitecture.test;

import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import edu.unq.desapp.groupA.backend.service.GenericService;

public class SimpleArchitectureTest {
	
	
    private static final String GETTER_METHOD_NAME_ID = "get";

    private static final String SETTER_METHOD_NAME_ID = "set";
    
    private static final String FIND_METHOD_NAME_ID = "find";

	
	
	@Test
	public void transactionalOnOtherServiceTest() {

		boolean isInitialServiceClassTransactional = false;
		boolean isClassTransactional = false;

		Class serviceClass = GenericService.class;

		isInitialServiceClassTransactional = this.checkTransactional(serviceClass);

		Object[] classes = this.getAllClassForPackage("edu.unq.desapp.groupA.backend.service"
				, GenericService.class);

		boolean retFinal = true;
		
		for (Object element : classes) {

			String rawClassName = element.toString();
			String[] className = rawClassName.split(" ");
			List<String> spArray = Arrays.asList(className);

			Class aClass = null;

			try {
				aClass = Class.forName(spArray.get(1));
				isClassTransactional = this.checkTransactional(aClass);				
				if(!isClassTransactional){
					System.out.println(spArray.get(1));
					break;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			

		}
		
		assertTrue(isInitialServiceClassTransactional && isClassTransactional);

	}


	public boolean checkTransactional(Class clase) {

		boolean retFinal = true;

		Method[] methods = clase.getDeclaredMethods();

		for (Method method : methods) {

			
			if (method.getName() != "getRepository" && method.getName() != "setRepository" &&
					isNotGetterOrSetterOrFindMethod(method)) {
				Annotation[] annot = method.getAnnotations();
 				int cont = 0;

				// if (annot.length > 0) {

				for (Annotation annotation : annot) {
					if (annotation.annotationType().getSimpleName().equals("Transactional"))
						cont++;

				}

				retFinal = (cont > 0);

				if (!retFinal)
					break;

			}

		}

		return retFinal;
	}

	
	private boolean isNotGetterOrSetterOrFindMethod(Method method) {
	        return !method.getName().contains(SETTER_METHOD_NAME_ID) && 
	        		!method.getName().contains(FIND_METHOD_NAME_ID) &&
	        		!method.getName().contains("lambda") &&
	        		!method.getName().contains("save") &&
	        		!method.getName().contains("update") &&
	        		!method.getName().contains(GETTER_METHOD_NAME_ID);
	}
	
	public Object[] getAllClassForPackage(String namePackage, Class clase){
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(namePackage))));
		
		return (reflections.getSubTypesOf(clase)).toArray();
	}

}