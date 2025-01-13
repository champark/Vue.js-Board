<template>
  <div id="app">
    <h1>Vue.js 게시판</h1>
    <PostForm @add-post="addPost" />
    <PostList :posts="posts" @delete-post="deletePost" @update-posts="updatePosts" />
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
    updatePosts(newPosts) {
      this.posts = newPosts; // Update the posts array when receiving new data
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
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
}

#app {
  font-family: Arial, sans-serif;
  text-align: center;
  margin: 20px;
}

h1 {
  color: #2c3e50;
  margin-bottom: 20px;
}
</style>
