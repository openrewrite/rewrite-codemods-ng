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
package org.openrewrite.codemods.ng;

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
        return "Applies Angular CLI";
    }

    @Override
    public String getDescription() {
        return "Applies Angular CLI.";
    }

    @Override
    protected List<String> getNpmCommand(Accumulator acc, ExecutionContext ctx) {
        List<String> command = new ArrayList<>();
        command.add("npx");
        command.add("@angular/cli@${version}");
        command.add("update");
        command.add("@angular/cli@${version}");
        command.add("@angular/core@${version}");
        
        // Replace `${version}` with the `version` value in each command item
        for (int i = 0; i < command.size(); i++) {
            String item = command.get(i);
            command.set(i, item.replace("${version}", Optional.ofNullable(version).orElse("latest")));
        }
        
        return command;
    }
}
