/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.kubernetes.examples;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.ListMeta;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;

public class ListExamples {

  private static final Logger logger = LoggerFactory.getLogger(ListExamples.class);

  public static void main(String[] args) {
    String master = "http://192.168.0.43:8080/";
    if (args.length == 1) {
      master = args[0];
    }

   Config config = new ConfigBuilder().withMasterUrl(master).build();
    
//    Config config = new ConfigBuilder().withMasterUrl(master)
//            .withTrustCerts(true)
//            .withUsername("root")
//            .withPassword("konyang")
//            .withNamespace("kube-system2")
//            .build();
   KubernetesClient client = new DefaultKubernetesClient(config);
   // KubernetesClient client = new AutoAdaptableKubernetesClient(config);
    
   // try (final KubernetesClient client = new DefaultKubernetesClient(config)) {
 try{
        NamespaceList nl =  client.namespaces().list();
        String apiVersion= nl.getApiVersion();
        String kind = nl.getKind();
        System.out.println("apiVersion : "+apiVersion +", kind : "+kind);
        ListMeta meta = nl.getMetadata();
        String rb = meta.getResourceVersion();
        String sl = meta.getSelfLink();
        String ap = meta.getAdditionalProperties().toString();
        System.out.println("rb : "+rb+", sl : "+ sl+", ap : "+ap);
        List<Namespace> names= nl.getItems();
        for (Namespace namespace : names) {
            String kindName = namespace.getKind();
            ObjectMeta om = namespace.getMetadata();
            om.getCreationTimestamp();//생성일
            om.getName();//스페이스 이름
            om.getUid();//uid
            om.getResourceVersion();
            om.getSelfLink();
            
            namespace.getSpec();
            
            namespace.getStatus().getAdditionalProperties();
        }
        
        
//        ListMeta meta = nl.getMetadata();
//        String kind = nl.getKind();
//        nl.getApiVersion();
//        List<Namespace> temp = nl.getItems();
//        meta.getResourceVersion();
//        meta.getSelfLink();
        
//        for (String name : nl) {
//            System.out.print(", ns : "+name);
//        }
//        nl =  client.namespaces().withLabel("this", "works").list();
//        for (String string : nl) {
//            
//        }

//      System.out.println(
//        client.namespaces().list()
//      );

//      System.out.println(
//        client.namespaces().withLabel("this", "works").list()
//      );

//      System.out.println(
//        client.pods().withLabel("this", "works").list()
//      );
//
     //==============================================
     
//      PodList pl = client.pods().inNamespace("kube-system2").list();
//      List<Pod> pods = pl.getItems();
//      for (Pod pod : pods) {
//          System.out.println(pod.getKind());
//          System.out.println(pod.getMetadata().getName());
//          System.out.println(pod.getMetadata().getNamespace());
//          System.out.println(pod.getMetadata().getGenerateName());
//          System.out.println(pod.getMetadata());
//    }
//      System.out.println(
//         client.pods().inNamespace("kube-system").withLabel("this", "works").list()
//       );
//
//      System.out.println(
//        client.pods().inNamespace("test").withName("testing").get()
//      );
    } catch (KubernetesClientException e) {
      logger.error(e.getMessage(), e);
    }
  }

}
