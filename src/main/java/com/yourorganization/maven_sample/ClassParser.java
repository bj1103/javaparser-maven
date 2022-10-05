package com.yourorganization.maven_sample;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.ast.body.*;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Some code that uses JavaParser.
 */
public class ClassParser {
    public static void main(String[] args) {
        Path path = Paths.get(args[0]);
        String class_path = path.getFileName().toString();
        String package_path = path.getParent().toString();
        
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(ClassParser.class).resolve(package_path));
        
        System.out.println("Parsing " + args[0]);
        CompilationUnit cu = sourceRoot.parse("", class_path);
        
        System.out.println("\n--- Attributes ---");
        cu.findAll(FieldDeclaration.class).forEach(
            attribute -> {
                attribute.findAll(VariableDeclarator.class).forEach(
                    variable -> {
                        StringBuilder sb = new StringBuilder();
                        attribute.getModifiers().forEach(modifier -> {
                            switch (modifier.getKeyword().asString()) {
                                case "public":
                                    sb.append("+ ");
                                    break;
                                case "private":
                                    sb.append("- ");
                                    break;
                                case "protected":
                                    sb.append("# ");
                                    break;
                                default:
                                    break;
                            }
                        });
                        sb.append(variable.getNameAsString() + " : " + variable.getTypeAsString());
                        String res = sb.toString();
                        System.out.println(res);
                    }
                ); 
            }
        );

        System.out.println("\n--- Methods ---");

        cu.findAll(MethodDeclaration.class).forEach(
            method -> {
                StringBuilder sb = new StringBuilder();
                method.getModifiers().forEach(modifier -> {
                    switch (modifier.getKeyword().asString()) {
                        case "public":
                            sb.append("+ ");
                            break;
                        case "private":
                            sb.append("- ");
                            break;
                        case "protected":
                            sb.append("# ");
                            break;
                        default:
                            break;
                    }
                });
                sb.append(method.getNameAsString());
                sb.append("(");
                // String prefix = "";
                ArrayList<String> parameters = new ArrayList<>();
                method.getParameters().forEach(parameter -> {
                    parameters.add(parameter.getNameAsString() + " : " + parameter.getTypeAsString());
                });
                sb.append(String.join(", ", parameters));
                sb.append(") : ");
                sb.append(method.getType().asString());
                String res = sb.toString();
                System.out.println(res);
            }
        );
    }
}
