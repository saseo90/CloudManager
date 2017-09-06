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

import io.fabric8.kubernetes.client.AutoAdaptableKubernetesClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

public class CredentialsExample {

    private static final Logger logger = LoggerFactory.getLogger(CredentialsExample.class);

    public static void main(String[] args) throws InterruptedException {
        String master = "http://192.168.0.43:8080/";
        if (args.length == 1) {
            master = args[0];
        }

        Config config = new ConfigBuilder().withMasterUrl(master)
          .withTrustCerts(true)
          .withUsername("root")
          .withPassword("konyang")
          .withNamespace("kube-system")
          .build();
        try (final KubernetesClient client = new AutoAdaptableKubernetesClient(config)) {

          log("Received pods", client.pods().list());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);


            Throwable[] suppressed = e.getSuppressed();
            if (suppressed != null) {
                for (Throwable t : suppressed) {
                    logger.error(t.getMessage(), t);
                }
            }
        }
    }

    private static void log(String action, Object obj) {
        logger.info("{}: {}", action, obj);
    }

    private static void log(String action) {
        logger.info(action);
    }
}
