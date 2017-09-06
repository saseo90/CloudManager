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

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.ServiceAccount;
import io.fabric8.kubernetes.api.model.ServiceAccountBuilder;
import io.fabric8.kubernetes.api.model.extensions.Deployment;
import io.fabric8.kubernetes.api.model.extensions.DeploymentBuilder;
import io.fabric8.kubernetes.client.AutoAdaptableKubernetesClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

public class DeploymentExamples {
  private static final Logger logger = LoggerFactory.getLogger(DeploymentExamples.class);

  public static void main(String[] args) throws InterruptedException {
      String master = "http://192.168.0.43:8080/";
      //Config config = new ConfigBuilder().withMasterUrl(master).build();
    Config config = new ConfigBuilder().withMasterUrl(master)
            .withTrustCerts(true)
            .withUsername("root")
            .withPassword("konyang")
            //.withNamespace("kube-system2")
            .build();
    //KubernetesClient client = new DefaultKubernetesClient(config);
    KubernetesClient client = new AutoAdaptableKubernetesClient(config);
    //log("Deleted namespace:", client.namespaces().withName("kube-system2").delete());
    try {
      // Create a namespace for all our stuff
      Namespace ns = new NamespaceBuilder().withNewMetadata().withName("kube-system2").addToLabels("this", "rocks").endMetadata().build();
//        Namespace ns = new NamespaceBuilder().withNewMetadata().withName("default").addToLabels("this", "rocks").endMetadata().build();
      log("Created namespace", client.namespaces().createOrReplace(ns));

      ServiceAccount fabric8 = new ServiceAccountBuilder().withNewMetadata().withName("kube-system2").endMetadata().build();

//      MIIEowIBAAKCAQEAxMVCmlCtMN7KsFIAyfjsFjZ8A0Ako0rygpviXlw88Tf9cxI6
//      ygG0pa069EP4hWB+/2L535QU/QJgpts4kMmw+tcjr8l0ZBGVMykNFi9bI1Qparnj
//      e+b159ushNytYDpGjTYOWviGYOqbwfUOTzhyZw4yGP5+C8XA7PzM9XBIpRYJrHoM
//      rxb/I34tIf/huikzgJUqGwMSK8/dD3qayRJVZUoTTYUtbM+GkujGdJ8xEC70JH1t
//      iyWlUxciTRIisbPRchWGBwdTtDQQt6OxqXvLBul0bRhkp/MgZTHgI6tTRkgsJRO4
//      1ivR2bd7opiB0t2zMJLTvYSx0hiUQrRtAMpIFwIDAQABAoIBADF5X5upOUKWYeQf
//      Wi9uNP4IyPmQQSYFjCAmza3oBzKnX0R1iu6Lqz4U5kPQa0Yt0wCnhCgg9X4AAQkz
//      CW/uJl+LhF+IiYk3mon4gl/XnFSvrpI1FutqUN3xL9lgGv+c52xN8SISZscrdeo4
//      kVZ93NXojdQW+N5A5JcxoN79UmafSOGZxFLAPE3K80GSMZ3RE/g4wmrAJlLeAnUr
//      HRcfeN9+vQN40bB4ggLP6NiB0oWPaL6xlNsvZM7L0ouqJ8AS7j84+o195stToWki
//      PyEBl6bjCpkULt3EbanISnGbMKTQJl5dG4nPZd1WP0qTVjXKQAdi6V/XMJsJ5t7q
//      KXpV0EECgYEA7Ai4cM3uWKsW0cmBC26gWEgA7Cy9Va6gj/taKSCAAfidY7tyY7bl
//      W+d61zaUEDqwDBruQmbObjzS4vW9onnomeDbi4nynXjkDlOM+mBbmMLz10Gw0Jzf
//      /PYQ7gcWEjuUkwgieUX3caNqRQgxzP0w3jsfvgv4hoKQulNfYd04zucCgYEA1WpL
//      aciqY7JdNg0vpdqj5F3ycQUxzXgtUq8ubBfMtbB6pwXJgLP8Nj0oNwL3OfMTvDl/
//      dAY6cV/llQOMv/FIDuxPTBNeKTWGzvST3mqkFeusgpnyGNBEVXTQxvRhw6rVOv0i
//      EEIDeVZ1iN5IfhGa9+uN21RC7Rn8dwVYlGADh1ECgYBWvU2qhsMUaqtUJQz7OohK
//      aQuNv1NE7Yur3d7gRkVEdCzCvdvnsQrQn1yRQklfPXH8wNjWJhmugUOujEhIMSy9
//      f9iuit9R519HtISWblq65MnfTRDBWSUnPPYzwv7lbGfJMLAaiQ/J/xfKjpxejJ0s
//      zeV4XcXTCKBPGNxwhm+d+QKBgHOREO9iw6ojL0iS0WeNw0xp91xuhK6QUnLA/HF6
//      XddRjsmdX6W7UWuJqJpJl9kgoWI+/J62tLUqSedsua5DGVNDchlvL4E/RBYT6XL6
//      6ooYSh2bAbegKDDihiAGvF45xygfZW/KX5k4UYNhY34pSHLScsn/khJVDRrioToP
//      2DlRAoGBAJ274v8WV0/dUNU4TAdXYQ+c9+1nfzDLaa5esJSbF6VHJYW0zG6Of2Uh
//      L1M2GUl7TTT2m/5/qgy+VqnKU3Ee8PYrhanY1LsuuYEe/ShXsEzG47Tgxt6zrxT8
//      srxvPNiqW7S4HcUWmYmWK8ZUtqP+c6U2apsqRSWYO2CSFBBYRsJ/
      client.serviceAccounts().inNamespace("kube-system2").createOrReplace(fabric8);
   //   for (int i = 0; i < 1; i++) {
          System.out.println("돈다1 : ");
//        System.err.println("Iteration:" + (i+1));
        Deployment deployment = new DeploymentBuilder()
          .withNewMetadata()
          .withName("korea")
          .endMetadata()
          .withNewSpec()
          .withReplicas(1)
          .withNewTemplate()
          .withNewMetadata()
          .addToLabels("app", "nginx1004")
          .endMetadata()
          .withNewSpec()
          .addNewContainer()
          .withName("111111111111111111111111111111111111111111111111111111111111")
          .withImage("gcr.io/google_containers/test-webserver")
          .addNewPort()
          .withContainerPort(80)
          .endPort()
          .endContainer()
          .endSpec()
          .endTemplate()
          .endSpec()
          .build();
        //client.replicationControllers().

        deployment = client.extensions().deployments().inNamespace("kube-system2").createOrReplace(deployment);
        //log("Created deployment", deployment);
        System.out.println("만들었다 : ");
//        System.err.println("Scaling up:" + deployment.getMetadata().getName());
     //   client.extensions().deployments().inNamespace("kube-system2").withName("pods2004").scale(2, true);
      //  log("Created replica sets:", client.extensions().replicaSets().inNamespace("kube-system2").list().getItems());
     //   System.err.println("Deleting:" + deployment.getMetadata().getName());
        //client.resource(deployment).delete();
     // }//for
      log("Done.");

    }finally {
    //  client.namespaces().withName("thisisatest").delete();
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
