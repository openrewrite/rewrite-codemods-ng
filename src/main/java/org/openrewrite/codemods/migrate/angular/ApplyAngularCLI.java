/*
 * Copyright 2023 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.codemods.migrate.angular;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Option;
import org.openrewrite.internal.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Value
@EqualsAndHashCode(callSuper = true)
public class ApplyAngularCLI extends NodeBasedRecipe {
    @Option(displayName = "Angular version",
            description = "Which version of Angular to upgrade",
            example = "17")
    @Nullable
    String version;

    @Override
    public String getDisplayName() {
        return "Upgrade Angular versions";
    }

    @Override
    public String getDescription() {
        return "Run `ng update` to upgrade Angular CLI and Angular Core to the specified version.";
    }

    @Override
    protected List<String> getNpmCommand(Accumulator acc, ExecutionContext ctx) {
        List<String> command = new ArrayList<>();
        command.add("ng");
        command.add("update");
        command.add(getAngularCliPackage(acc, ctx));
        command.add("@angular/core@${version}");
        
        // Replace `${version}` with the `version` value in each command item
        command.replaceAll(s -> s.replace("${version}", Optional.ofNullable(version).orElse("latest")));
        
        return command;
    }

    @Override
    protected String getAngularCliPackage(Accumulator acc, ExecutionContext ctx) {
        return "@angular/cli@${version}".replace("${version}", Optional.ofNullable(version).orElse("latest"));
    }

    @Override
    protected boolean useNvmExec(Accumulator acc, ExecutionContext ctx) {
        // parse the version to an integer
        // if the version is below 15, use nvm exec
        return Integer.parseInt(Optional.ofNullable(version).orElse("0")) < 15;
    }
}
