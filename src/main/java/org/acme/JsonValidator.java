package org.acme;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class JsonValidator {

    private final SchemaService schemaService;

    public void validate(final JsonNode jsonNode) {
        final String dummySchema = schemaService.getSchema();

        final JsonSchemaFactory factory = com.networknt.schema.JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        final JsonSchema jsonSchema = factory.getSchema(dummySchema);

        final Set<ValidationMessage> errorMessage = jsonSchema.validate(jsonNode);
        if (CollectionUtils.isNotEmpty(errorMessage)) {
            final String errorMessages = errorMessage.stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
            throw new InvalidParameterException(errorMessages);
        }
    }
}
