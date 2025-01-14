<template>
  <div>
    <div v-for="post in posts" :key="post.id" class="post">
      <div class="post-content">
        <h2>{{ post.title }}</h2>
        <p>{{ post.content }}</p>
      </div>
      <div class="post-actions">
        <button class="update-btn" @click="$emit('edit-post', post.id)">Edit</button>
        <button class="delete-btn" @click="$emit('delete-post', post.id)">Delete</button>
      </div>
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
  margin: 10px 0;
  padding: 20px;
  max-width: 600px;
  min-height: 500px;
  background: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column; /* Stack content vertically */
  justify-content: space-between; /* Ensure space is used efficiently */
}

.post-content {
  margin-bottom: 20px; /* Add space between content and buttons */
}

.post-actions {
  display: flex;
  justify-content: flex-start; /* Align buttons to the left */
  gap: 10px; /* Add space between the buttons */
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
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
}

.update-btn {
  background-color: #4CAF50;
  color: white;
}

.update-btn:hover {
  background-color: #45a049; /* Slightly darker green */
}

.delete-btn {
  background-color: #f44336; /* Red background for the delete button */
  color: white;
}

.delete-btn:hover {
  background-color: #c0392b;
}

.div_container {
  display: flex;
  align-items: flex-start;
}
</style>
