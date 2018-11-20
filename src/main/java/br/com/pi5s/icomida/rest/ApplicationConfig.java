package br.com.pi5s.icomida.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * @author Guilherme Abacherli
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.pi5s.icomida.rest.CORSFilter.class);
        resources.add(br.com.pi5s.icomida.rest.IcomidaResource.class);
    }
}