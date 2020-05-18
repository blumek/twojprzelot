package pl.twojprzelot.backend.application.spring_app.command;

import lombok.Getter;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Getter
@Command(name = "baseCommand")
public class BaseCommand {
    @Option(names = {"-aekey", "--aviationEdgeKey"}, required = true)
    private String aviationEdgeKey;
}
