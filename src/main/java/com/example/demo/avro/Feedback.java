/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.demo.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Feedback extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4654124770428341404L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Feedback\",\"namespace\":\"com.example.demo.avro\",\"fields\":[{\"name\":\"taskId\",\"type\":\"string\"},{\"name\":\"projectId\",\"type\":\"string\"},{\"name\":\"scenarioId\",\"type\":\"string\"},{\"name\":\"system_name\",\"type\":\"string\"},{\"name\":\"return_value\",\"type\":\"string\"},{\"name\":\"return_time\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Feedback> ENCODER =
      new BinaryMessageEncoder<Feedback>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Feedback> DECODER =
      new BinaryMessageDecoder<Feedback>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Feedback> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Feedback> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Feedback>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Feedback to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Feedback from a ByteBuffer. */
  public static Feedback fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence taskId;
  @Deprecated public java.lang.CharSequence projectId;
  @Deprecated public java.lang.CharSequence scenarioId;
  @Deprecated public java.lang.CharSequence system_name;
  @Deprecated public java.lang.CharSequence return_value;
  @Deprecated public long return_time;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Feedback() {}

  /**
   * All-args constructor.
   * @param taskId The new value for taskId
   * @param projectId The new value for projectId
   * @param scenarioId The new value for scenarioId
   * @param system_name The new value for system_name
   * @param return_value The new value for return_value
   * @param return_time The new value for return_time
   */
  public Feedback(java.lang.CharSequence taskId, java.lang.CharSequence projectId, java.lang.CharSequence scenarioId, java.lang.CharSequence system_name, java.lang.CharSequence return_value, java.lang.Long return_time) {
    this.taskId = taskId;
    this.projectId = projectId;
    this.scenarioId = scenarioId;
    this.system_name = system_name;
    this.return_value = return_value;
    this.return_time = return_time;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return taskId;
    case 1: return projectId;
    case 2: return scenarioId;
    case 3: return system_name;
    case 4: return return_value;
    case 5: return return_time;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: taskId = (java.lang.CharSequence)value$; break;
    case 1: projectId = (java.lang.CharSequence)value$; break;
    case 2: scenarioId = (java.lang.CharSequence)value$; break;
    case 3: system_name = (java.lang.CharSequence)value$; break;
    case 4: return_value = (java.lang.CharSequence)value$; break;
    case 5: return_time = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'taskId' field.
   * @return The value of the 'taskId' field.
   */
  public java.lang.CharSequence getTaskId() {
    return taskId;
  }

  /**
   * Sets the value of the 'taskId' field.
   * @param value the value to set.
   */
  public void setTaskId(java.lang.CharSequence value) {
    this.taskId = value;
  }

  /**
   * Gets the value of the 'projectId' field.
   * @return The value of the 'projectId' field.
   */
  public java.lang.CharSequence getProjectId() {
    return projectId;
  }

  /**
   * Sets the value of the 'projectId' field.
   * @param value the value to set.
   */
  public void setProjectId(java.lang.CharSequence value) {
    this.projectId = value;
  }

  /**
   * Gets the value of the 'scenarioId' field.
   * @return The value of the 'scenarioId' field.
   */
  public java.lang.CharSequence getScenarioId() {
    return scenarioId;
  }

  /**
   * Sets the value of the 'scenarioId' field.
   * @param value the value to set.
   */
  public void setScenarioId(java.lang.CharSequence value) {
    this.scenarioId = value;
  }

  /**
   * Gets the value of the 'system_name' field.
   * @return The value of the 'system_name' field.
   */
  public java.lang.CharSequence getSystemName() {
    return system_name;
  }

  /**
   * Sets the value of the 'system_name' field.
   * @param value the value to set.
   */
  public void setSystemName(java.lang.CharSequence value) {
    this.system_name = value;
  }

  /**
   * Gets the value of the 'return_value' field.
   * @return The value of the 'return_value' field.
   */
  public java.lang.CharSequence getReturnValue() {
    return return_value;
  }

  /**
   * Sets the value of the 'return_value' field.
   * @param value the value to set.
   */
  public void setReturnValue(java.lang.CharSequence value) {
    this.return_value = value;
  }

  /**
   * Gets the value of the 'return_time' field.
   * @return The value of the 'return_time' field.
   */
  public java.lang.Long getReturnTime() {
    return return_time;
  }

  /**
   * Sets the value of the 'return_time' field.
   * @param value the value to set.
   */
  public void setReturnTime(java.lang.Long value) {
    this.return_time = value;
  }

  /**
   * Creates a new Feedback RecordBuilder.
   * @return A new Feedback RecordBuilder
   */
  public static com.example.demo.avro.Feedback.Builder newBuilder() {
    return new com.example.demo.avro.Feedback.Builder();
  }

  /**
   * Creates a new Feedback RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Feedback RecordBuilder
   */
  public static com.example.demo.avro.Feedback.Builder newBuilder(com.example.demo.avro.Feedback.Builder other) {
    return new com.example.demo.avro.Feedback.Builder(other);
  }

  /**
   * Creates a new Feedback RecordBuilder by copying an existing Feedback instance.
   * @param other The existing instance to copy.
   * @return A new Feedback RecordBuilder
   */
  public static com.example.demo.avro.Feedback.Builder newBuilder(com.example.demo.avro.Feedback other) {
    return new com.example.demo.avro.Feedback.Builder(other);
  }

  /**
   * RecordBuilder for Feedback instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Feedback>
    implements org.apache.avro.data.RecordBuilder<Feedback> {

    private java.lang.CharSequence taskId;
    private java.lang.CharSequence projectId;
    private java.lang.CharSequence scenarioId;
    private java.lang.CharSequence system_name;
    private java.lang.CharSequence return_value;
    private long return_time;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.demo.avro.Feedback.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.taskId)) {
        this.taskId = data().deepCopy(fields()[0].schema(), other.taskId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.projectId)) {
        this.projectId = data().deepCopy(fields()[1].schema(), other.projectId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.scenarioId)) {
        this.scenarioId = data().deepCopy(fields()[2].schema(), other.scenarioId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.system_name)) {
        this.system_name = data().deepCopy(fields()[3].schema(), other.system_name);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.return_value)) {
        this.return_value = data().deepCopy(fields()[4].schema(), other.return_value);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.return_time)) {
        this.return_time = data().deepCopy(fields()[5].schema(), other.return_time);
        fieldSetFlags()[5] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Feedback instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.demo.avro.Feedback other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.taskId)) {
        this.taskId = data().deepCopy(fields()[0].schema(), other.taskId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.projectId)) {
        this.projectId = data().deepCopy(fields()[1].schema(), other.projectId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.scenarioId)) {
        this.scenarioId = data().deepCopy(fields()[2].schema(), other.scenarioId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.system_name)) {
        this.system_name = data().deepCopy(fields()[3].schema(), other.system_name);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.return_value)) {
        this.return_value = data().deepCopy(fields()[4].schema(), other.return_value);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.return_time)) {
        this.return_time = data().deepCopy(fields()[5].schema(), other.return_time);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'taskId' field.
      * @return The value.
      */
    public java.lang.CharSequence getTaskId() {
      return taskId;
    }

    /**
      * Sets the value of the 'taskId' field.
      * @param value The value of 'taskId'.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder setTaskId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.taskId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'taskId' field has been set.
      * @return True if the 'taskId' field has been set, false otherwise.
      */
    public boolean hasTaskId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'taskId' field.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder clearTaskId() {
      taskId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'projectId' field.
      * @return The value.
      */
    public java.lang.CharSequence getProjectId() {
      return projectId;
    }

    /**
      * Sets the value of the 'projectId' field.
      * @param value The value of 'projectId'.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder setProjectId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.projectId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'projectId' field has been set.
      * @return True if the 'projectId' field has been set, false otherwise.
      */
    public boolean hasProjectId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'projectId' field.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder clearProjectId() {
      projectId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'scenarioId' field.
      * @return The value.
      */
    public java.lang.CharSequence getScenarioId() {
      return scenarioId;
    }

    /**
      * Sets the value of the 'scenarioId' field.
      * @param value The value of 'scenarioId'.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder setScenarioId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.scenarioId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'scenarioId' field has been set.
      * @return True if the 'scenarioId' field has been set, false otherwise.
      */
    public boolean hasScenarioId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'scenarioId' field.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder clearScenarioId() {
      scenarioId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'system_name' field.
      * @return The value.
      */
    public java.lang.CharSequence getSystemName() {
      return system_name;
    }

    /**
      * Sets the value of the 'system_name' field.
      * @param value The value of 'system_name'.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder setSystemName(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.system_name = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'system_name' field has been set.
      * @return True if the 'system_name' field has been set, false otherwise.
      */
    public boolean hasSystemName() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'system_name' field.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder clearSystemName() {
      system_name = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'return_value' field.
      * @return The value.
      */
    public java.lang.CharSequence getReturnValue() {
      return return_value;
    }

    /**
      * Sets the value of the 'return_value' field.
      * @param value The value of 'return_value'.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder setReturnValue(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.return_value = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'return_value' field has been set.
      * @return True if the 'return_value' field has been set, false otherwise.
      */
    public boolean hasReturnValue() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'return_value' field.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder clearReturnValue() {
      return_value = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'return_time' field.
      * @return The value.
      */
    public java.lang.Long getReturnTime() {
      return return_time;
    }

    /**
      * Sets the value of the 'return_time' field.
      * @param value The value of 'return_time'.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder setReturnTime(long value) {
      validate(fields()[5], value);
      this.return_time = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'return_time' field has been set.
      * @return True if the 'return_time' field has been set, false otherwise.
      */
    public boolean hasReturnTime() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'return_time' field.
      * @return This builder.
      */
    public com.example.demo.avro.Feedback.Builder clearReturnTime() {
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Feedback build() {
      try {
        Feedback record = new Feedback();
        record.taskId = fieldSetFlags()[0] ? this.taskId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.projectId = fieldSetFlags()[1] ? this.projectId : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.scenarioId = fieldSetFlags()[2] ? this.scenarioId : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.system_name = fieldSetFlags()[3] ? this.system_name : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.return_value = fieldSetFlags()[4] ? this.return_value : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.return_time = fieldSetFlags()[5] ? this.return_time : (java.lang.Long) defaultValue(fields()[5]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Feedback>
    WRITER$ = (org.apache.avro.io.DatumWriter<Feedback>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Feedback>
    READER$ = (org.apache.avro.io.DatumReader<Feedback>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}