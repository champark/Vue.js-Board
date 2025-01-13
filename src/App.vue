<template>
  <div id="app">
    <h1>Vue.js 게시판</h1>
    <PostForm @add-post="addPost" />
    <PostList :posts="posts" @delete-post="deletePost" />
  </div>
</template>

<script>
import PostForm from './components/PostForm.vue';
import PostList from './components/PostList.vue';

export default {
  name: 'App',
  components: { PostForm, PostList },
  data() {
    return {
      posts: [
        { id: 1, title: 'First Post', content: 'This is the first post' },
        { id: 2, title: 'Second Post', content: 'This is the second post' },
      ],
    };
  },
  methods: {
    addPost(newPost) {
      const id = this.posts.length + 1;
      this.posts.push({ id, ...newPost });
    },
    deletePost(postId) {
      this.posts = this.posts.filter((post) => post.id !== postId);
    },
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
