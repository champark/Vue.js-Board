<template>
  <div id="app">
    <h1>Vue.js 게시판</h1>
    <PostForm @add-post="addPost" />
    <hr class="divider" />
    <PostList :posts="posts" @delete-post="deletePost" @edit-post="editPost" />
  </div>
</template>

<script>
import PostForm from './components/PostForm.vue';
import PostList from './components/PostList.vue';
import axios from 'axios';

export default {
  name: 'App',
  components: { PostForm, PostList },
  data() {
    return {
      posts: [],
    };
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get('http://localhost:9000/api/posts');
        this.posts = response.data;
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
    },
    async addPost(newPost) {
      try {
        const response = await axios.post('http://localhost:9000/api/posts', newPost);
        this.posts.push(response.data);
      } catch (error) {
        console.error('Error adding post:', error);
      }
    },
    async deletePost(postId) {
      try {
        console.log('삭제버튼 클릭감지');
        await axios.delete(`http://localhost:9000/api/posts/${postId}`);
        this.posts = this.posts.filter(post => post.id !== postId);
      } catch (error) {
        console.error('Error deleting post:', error);
      }
    },
    async editPost(postId) {
      const post = this.posts.find(post => post.id === postId);
      if (post) {
        const updatedContent = prompt('Edit the content:', post.content);
        if (updatedContent !== null && updatedContent !== '') {
          const updatedPost = { ...post, content: updatedContent };
          try {
            const response = await axios.put(`http://localhost:9000/api/posts/${postId}`, updatedPost);
            Object.assign(post, response.data); // Update the local post
          } catch (error) {
            console.error('Error updating post:', error); // Log the error for debugging
          }
        }
      }
    },
  },
  mounted() {
    this.fetchPosts();
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 10px;
  margin-bottom: 20px;
}

#app {
  font-family: Arial, sans-serif;
  text-align: left;
  margin: 20px;
}

h1 {
  color: #ffffff;
  background-color: #4CAF50;
  padding: 15px 20px;
  border-radius: 8px;
  text-align: center;
  font-weight: bold;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
  animation: fadeIn 1s ease-in-out; /* Fade-in animation */
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px); /* Slide in from the top */
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}


.divider {
  height: 1px;
  background-color: #ddd;
  border: none;
  margin: 20px 0;
}
</style>
