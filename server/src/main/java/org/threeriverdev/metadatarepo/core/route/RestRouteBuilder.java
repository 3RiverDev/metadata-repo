package org.threeriverdev.metadatarepo.core.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.threeriverdev.metadatarepo.core.model.Artifact;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class RestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .contextPath("metadata-repo-server/rest").port(8080);

//        rest("/provider").description("Provider rest service")
//                .consumes("application/json").produces("application/json")
//
//                .get("/{id}").description("Find provider by id").outType(Provider.class)
//                .to("bean:providerService?method=getProvider(${header.id})")
//
//                .put().description("Updates or create a provider").type(Provider.class)
//                .to("bean:providerService?method=updateProvider")
//
//                .get().description("List all providers").outTypeList(Provider.class)
//                .to("bean:providerService?method=listProviders")
//
//                .get("/search").description("Search by Zip").outTypeList(Provider.class)
//                .route().log("Incoming zip: ${header.zip}")
//                .to("bean:providerService?method=searchByZip(${header.zip})").endRest();

        rest("/test")
                .get("/").outTypeList(Artifact.class)
                .to("bean:artifactRetriever?method=getAll");
    }
}
