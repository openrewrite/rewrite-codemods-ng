/*
 * Copyright 2024 the original author or authors.
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.test.SourceSpecs.text;

@DisabledIfEnvironmentVariable(named = "CI", matches = "true")
public class ApplyAngularCLITest implements RewriteTest {

  @Test
  void formatAngularStatement() {

    rewriteRun(
        spec -> spec.recipe(new ApplyAngularCLI("16")),
        text(
            // language=js
            """
                {
                             "$schema": "./node_modules/@angular/cli/lib/config/schema.json",
                             "version": 1,
                             "newProjectRoot": "projects",
                             "projects": {
                               "src": {
                                 "projectType": "application",
                                 "schematics": {},
                                 "root": "",
                                 "sourceRoot": "src",
                                 "prefix": "app",
                                 "architect": {
                                   "build": {
                                     "builder": "@angular-devkit/build-angular:application",
                                     "options": {
                                       "outputPath": "dist/src",
                                       "index": "src/index.html",
                                       "browser": "src/main.ts",
                                       "polyfills": [
                                         "zone.js"
                                       ],
                                       "tsConfig": "tsconfig.app.json",
                                       "assets": [
                                         "src/favicon.ico",
                                         "src/assets"
                                       ],
                                       "styles": [
                                         "src/styles.css"
                                       ],
                                       "scripts": []
                                     },
                                     "configurations": {
                                       "production": {
                                         "budgets": [
                                           {
                                             "type": "initial",
                                             "maximumWarning": "500kb",
                                             "maximumError": "1mb"
                                           },
                                           {
                                             "type": "anyComponentStyle",
                                             "maximumWarning": "2kb",
                                             "maximumError": "4kb"
                                           }
                                         ],
                                         "outputHashing": "all"
                                       },
                                       "development": {
                                         "optimization": false,
                                         "extractLicenses": false,
                                         "sourceMap": true
                                       }
                                     },
                                     "defaultConfiguration": "production"
                                   },
                                   "serve": {
                                     "builder": "@angular-devkit/build-angular:dev-server",
                                     "configurations": {
                                       "production": {
                                         "buildTarget": "src:build:production"
                                       },
                                       "development": {
                                         "buildTarget": "src:build:development"
                                       }
                                     },
                                     "defaultConfiguration": "development"
                                   },
                                   "extract-i18n": {
                                     "builder": "@angular-devkit/build-angular:extract-i18n",
                                     "options": {
                                       "buildTarget": "src:build"
                                     }
                                   },
                                   "test": {
                                     "builder": "@angular-devkit/build-angular:karma",
                                     "options": {
                                       "polyfills": [
                                         "zone.js",
                                         "zone.js/testing"
                                       ],
                                       "tsConfig": "tsconfig.spec.json",
                                       "assets": [
                                         "src/favicon.ico",
                                         "src/assets"
                                       ],
                                       "styles": [
                                         "src/styles.css"
                                       ],
                                       "scripts": []
                                     }
                                   }
                                 }
                               }
                             }
                           }

                """,
            """
                {
                              "$schema": "./node_modules/@angular/cli/lib/config/schema.json",
                              "version": 1,
                              "newProjectRoot": "projects",
                              "projects": {
                                "src": {
                                  "projectType": "application",
                                  "schematics": {},
                                  "root": "",
                                  "sourceRoot": "src",
                                  "prefix": "app",
                                  "architect": {
                                    "build": {
                                      "builder": "@angular-devkit/build-angular:application",
                                      "options": {
                                        "outputPath": "dist/src",
                                        "index": "src/index.html",
                                        "browser": "src/main.ts",
                                        "polyfills": [
                                          "zone.js"
                                        ],
                                        "tsConfig": "tsconfig.app.json",
                                        "assets": [
                                          "src/favicon.ico",
                                          "src/assets"
                                        ],
                                        "styles": [
                                          "src/styles.css"
                                        ],
                                        "scripts": []
                                      },
                                      "configurations": {
                                        "production": {
                                          "budgets": [
                                            {
                                              "type": "initial",
                                              "maximumWarning": "500kb",
                                              "maximumError": "1mb"
                                            },
                                            {
                                              "type": "anyComponentStyle",
                                              "maximumWarning": "2kb",
                                              "maximumError": "4kb"
                                            }
                                          ],
                                          "outputHashing": "all"
                                        },
                                        "development": {
                                          "optimization": false,
                                          "extractLicenses": false,
                                          "sourceMap": true
                                        }
                                      },
                                      "defaultConfiguration": "production"
                                    },
                                    "serve": {
                                      "builder": "@angular-devkit/build-angular:dev-server",
                                      "configurations": {
                                        "production": {
                                          "buildTarget": "src:build:production"
                                        },
                                        "development": {
                                          "buildTarget": "src:build:development"
                                        }
                                      },
                                      "defaultConfiguration": "development"
                                    },
                                    "extract-i18n": {
                                      "builder": "@angular-devkit/build-angular:extract-i18n",
                                      "options": {
                                        "buildTarget": "src:build"
                                      }
                                    },
                                    "test": {
                                      "builder": "@angular-devkit/build-angular:karma",
                                      "options": {
                                        "polyfills": [
                                          "zone.js",
                                          "zone.js/testing"
                                        ],
                                        "tsConfig": "tsconfig.spec.json",
                                        "assets": [
                                          "src/favicon.ico",
                                          "src/assets"
                                        ],
                                        "styles": [
                                          "src/styles.css"
                                        ],
                                        "scripts": []
                                      }
                                    }
                                  }
                                }
                              }
                            }

                """,
            spec -> spec.path("angular.json")));
  }
}
