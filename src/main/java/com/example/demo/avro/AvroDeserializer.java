package com.example.demo.avro;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Map;

/**
 *  avro反序列化
 * @author xcp
 * @creat 2020-03-12-15:19
 */
public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    //日志系统
    private static final Logger LOGGER = LoggerFactory.getLogger(AvroDeserializer.class);

    protected final Class<T> targetType;

    public AvroDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }
    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {}

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            T result = null;
            if(data == null) {
                return null;
            }
            LOGGER.debug("data='{}'", DatatypeConverter.printHexBinary(data));
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            DatumReader<GenericRecord> userDatumReader = new SpecificDatumReader<>(targetType.newInstance().getSchema());
            BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(in, null);
            result = (T) userDatumReader.read(null, decoder);
            LOGGER.debug("deserialized data='{}'", result);
            return result;
        } catch (Exception ex) {
            throw new SerializationException(
                    "Can't deserialize data '" + Arrays.toString(data) + "' from topic '" + topic + "'", ex);
        } finally {

        }
    }
}