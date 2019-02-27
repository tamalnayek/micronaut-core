/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.inject.factory.beanwithfactory

import io.micronaut.context.BeanContext
import io.micronaut.context.DefaultBeanContext
import spock.lang.Specification

class BeanInitializingWithFactorySpec extends Specification {

    void "test bean initializing event listener"() {
        given:
        BeanContext context = new DefaultBeanContext().start()

        when:"A bean is retrieved where a BeanInitializedEventListener is present"
        B b = context.getBean(B)

        then:"The event is triggered prior to @PostConstruct hooks"
        b.name == "CHANGED"

        when:"a bean is retrieved that is both a created and initialized listener"
        DualListener listener = context.getBean(DualListener)

        then:
        listener.initialized
        listener.created

    }
}

