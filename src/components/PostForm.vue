<template>
  <form @submit.prevent="submitForm">
    <input v-model="title" type="text" placeholder="Post Title" required />
    <textarea v-model="content" placeholder="Post Content" required></textarea>
    <button type="submit">Add Post</button>
  </form>
</template>

<script>
export default {
  name: 'PostForm',
  data() {
    return {
      title: '',
      content: '',
    };
  },
  methods: {
    submitForm() {
      this.$emit('add-post', { title: this.title, content: this.content });
      this.title = '';
      this.content = '';
    },
  },
};
</script>

<style scoped>
input, textarea {
  width: 90%;
  max-width: 500px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

textarea {
  height: 100px;
}

button {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #2980b9;
}
</style>