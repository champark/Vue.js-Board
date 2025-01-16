<template>
  <div v-if="show" class="modal-overlay">
    <div class="modal-content">
      <h3>{{ title }}</h3>
      <textarea v-model="inputValue" placeholder="Enter new content"></textarea>
      <div class="modal-actions">
        <button @click="confirm">Confirm</button>
        <button @click="close">Cancel</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EditModal', // Multi-word name
  props: {
    show: {
      type: Boolean,
      required: true,
    },
    title: {
      type: String,
      default: "Edit Content",
    },
    defaultValue: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      inputValue: this.defaultValue, // Initialize with default value
    };
  },
  methods: {
    confirm() {
      this.$emit("confirm", this.inputValue); // Emit the updated value
    },
    close() {
      this.$emit("close"); // Emit close event
    },
  },
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

button {
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:first-of-type {
  background-color: #4CAF50;
  color: white;
}

button:last-of-type {
  background-color: #f44336;
  color: white;
}
</style>
