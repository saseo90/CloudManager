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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;

public class DeleteExamples {

  private static final Logger logger = LoggerFactory.getLogger(DeleteExamples.class);

  public static void main(String[] args) {
    //String master = "https://localhost:8443/";
      String master = "http://192.168.0.43:8080/";
    if (args.length == 1) {
      master = args[0];
    }
    Config config = new ConfigBuilder().withMasterUrl(master)
            .withTrustCerts(true)
            .withUsername("root")
            .withPassword("konyang")
            //.withNamespace("kube-system2")
            .build();

    //Config config = new ConfigBuilder().withMasterUrl(master).build();
    KubernetesClient client = new DefaultKubernetesClient(config);
//    ServiceAccount fabric8 = new ServiceAccountBuilder().withNewMetadata().withName("kube-system2").endMetadata().build();
//    client.serviceAccounts().inNamespace("kube-system2").createOrReplace(fabric8);
//    KubernetesClient client = new AutoAdaptableKubernetesClient(config);
    try {
    //  log("Create namespace:", client.namespaces().create(new NamespaceBuilder().withawwNewMetadata().withName("kube-system2").endMetadata().build()));

        //log("Deleted namespace:", client.namespaces().withName("kube-system2").delete());
        
      log("Deleted testPod:", client.pods().inNamespace("kube-system2").withName("korea-3726231930-ieegq").delete());
        
//      log("Deleted pod by label:", client.pods().withLabel("this", "works").delete());
    } catch (KubernetesClientException e) {
      logger.error(e.getMessage(), e);
    } finally {
     // client.namespaces().withName("thisisatest").delete();
      client.close();
    }
  }

  private static void log(String action, Object obj) {
    logger.info("{}: {}", action, obj);
  }

  private static void log(String action) {
    logger.info(action);
  }

}
