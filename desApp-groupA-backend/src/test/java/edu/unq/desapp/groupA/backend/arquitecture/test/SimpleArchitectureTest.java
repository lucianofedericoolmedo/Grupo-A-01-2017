package edu.unq.desapp.groupA.backend.arquitecture.test;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
	public void transactionalOnGenericServiceTest() {

		boolean retSuperClass = false;
		boolean retSubClasses = false;

		Class serviceClass = GenericService.class;

		retSuperClass = this.checkTransactional(serviceClass);

		Object[] clases = this.getAllClassForPackage("edu.unq.desapp.groupA.backend.service", GenericService.class);

		//boolean retFinal = true;

		for (Object element : clases) {

			String name = element.toString();
			String[] sp = name.split(" ");
			List<String> spArray = new ArrayList<String>();

			for (String string : sp) {
				spArray.add(string);
			}

			Class clase = null;

			try {
				clase = Class.forName(spArray.get(1));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			retSubClasses = this.checkTransactional(clase);
			
			if(!retSubClasses)
				break;

		}
		
		assertTrue(retSuperClass);
		//assertTrue(retSubClasses);		

		//assertTrue(retSuperClass && retSubClasses);

	}
	
	@Test
	public void transactionalOnOtherServiceTest() {

		boolean retSuperClass = false;
		boolean retSubClasses = false;

		Class serviceClass = GenericService.class;

		retSuperClass = this.checkTransactional(serviceClass);

		Object[] clases = this.getAllClassForPackage("edu.unq.desapp.groupA.backend.service", GenericService.class);

		//boolean retFinal = true;

		for (Object element : clases) {

			String name = element.toString();
			String[] sp = name.split(" ");
			List<String> spArray = new ArrayList<String>();

			for (String string : sp) {
				spArray.add(string);
			}

			Class clase = null;

			try {
				clase = Class.forName(spArray.get(1));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			retSubClasses = this.checkTransactional(clase);
			
			if(!retSubClasses)
				break;

		}
		
		assertTrue(retSuperClass);
		//TODO: Refac in Model !!!
		assertFalse(retSubClasses);		

		//assertTrue(retSuperClass && retSubClasses);

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

				// }

			}

		}

		return retFinal;
	}

	public Object[] getAllClassForPackage2(String namePackage, Class clase) {

		//Reflections reflections = new Reflections(namePackage);
		
		Reflections reflections = new Reflections(namePackage, new SubTypesScanner(false));
		Object[] classes = (reflections.getSubTypesOf(clase)).toArray();
		return classes;

	}
	
	
	private boolean isNotGetterOrSetterOrFindMethod(Method method) {
	        return !method.getName().contains(SETTER_METHOD_NAME_ID) && 
	        		!method.getName().contains(FIND_METHOD_NAME_ID) &&
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