<template>
  <div>
    <div v-for="post in posts" :key="post.id" class="post">
      <h2>{{ post.title }}</h2>
      <p>{{ post.content }}</p>
      <button @click="$emit('delete-post', post.id)">Delete</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'PostList',
  props: ['posts'],
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('http://localhost:9000/api/posts');
        this.$emit('update-posts', response.data); // Emit the fetched posts to the parent
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
    },
  },
  mounted() {
    this.fetchPosts(); // Call fetchPosts when the component is mounted
  },
};
</script>

<style scoped>
.post {
  margin: 10px auto;
  padding: 20px;
  max-width: 600px;
  background: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h2 {
  margin: 0;
  color: #34495e;
}

p {
  margin: 10px 0;
  color: #555;
}

button {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

button:hover {
  background-color: #c0392b;
}
</style>
