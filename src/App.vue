<template>
  <div id="app">
    <h1>Vue.js 게시판</h1>
    <PostForm @add-post="addPost" />
    <hr class="divider" />
    <PostList
        :posts="posts"
        @edit-post="openModal"
        @delete-post="deletePost"
    />
    <EditModal
        v-if="isModalOpen"
        :show="isModalOpen"
        :title="'Edit Post Content'"
        :defaultValue="modalContent"
        @confirm="editPost"
        @close="closeModal"
    />
    <div :class="['notification', { show: notification.show }]">{{ notification.message }}</div>

  </div>
</template>

<script>
import PostForm from './components/PostForm.vue';
import PostList from './components/PostList.vue';
import axios from 'axios';
import EditModal from "@/components/EditModal";

export default {
  name: 'App',
  components: {EditModal, PostForm, PostList },
  data() {
    return {
      isModalOpen: false, // Define the property and set the initial value
      modalPostId: null,
      modalContent: "",
      posts: [],
      notification: {
        show: false,
        message: "",
      },
    };
  },
  methods: {
    showNotification(message) {
      this.notification.message = message;
      this.notification.show = true;

      // Hide the notification after 3 seconds
      setTimeout(() => {
        this.notification.show = false;
      }, 3000);
    },
    openModal(postId) {
      const post = this.posts.find((p) => p.id === postId);
      if (post) {
        this.modalPostId = postId;
        this.modalContent = post.content;
        this.isModalOpen = true;
      }
    },
    closeModal() {
      this.isModalOpen = false;
    },
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
    async editPost(newContent) {
      const post = this.posts.find((p) => p.id === this.modalPostId);
      if (post) {
        try {
          // Send the updated content to the backend
          await axios.put(`http://localhost:9000/api/posts/${post.id}`, {
            title: post.title, // Keep the title unchanged
            content: newContent, // Update the content
          });

          // Update the local post content after a successful request
          post.content = newContent;

          // Show a success notification
          this.showNotification("글 수정을 성공했습니다");
        } catch (error) {
          console.error("Error updating post:", error);
          this.showNotification("Failed to update the post. Please try again.");
        }
      }
      this.closeModal();
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

.notification {
  position: fixed;
  top: 20px;
  right: 20px;
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  opacity: 0;
  transform: translateY(-20px);
  transition: opacity 0.5s ease, transform 0.5s ease;
}

.notification.show {
  opacity: 1;
  transform: translateY(0);
}

</style>
