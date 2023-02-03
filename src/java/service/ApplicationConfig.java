/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Set;
import javax.ws.rs.core.Application;

/*
 * @author 2dam
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /* Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AlbumFacadeREST.class);
        resources.add(service.ContentFacadeREST.class);
        resources.add(service.CustomImageFacadeREST.class);
        resources.add(service.CustomTextFacadeREST.class);
        resources.add(service.MemberFacadeREST.class);
        resources.add(service.MembershipPlanFacadeREST.class);
        resources.add(service.UserFacadeREST.class);
    }

}
