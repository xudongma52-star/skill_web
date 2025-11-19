<template>
  <div class="petal-container">
    <!-- 动态生成花瓣元素 -->
    <div
        v-for="(petal, index) in petals"
        :key="index"
        class="petal"
        :style="{
        left: `${petal.left}%`,
        top: `-${petal.size}px`,
        width: `${petal.size}px`,
        height: `${petal.size}px`,
        background: petal.color,
        animationDuration: `${petal.duration}s`,
        animationDelay: `${petal.delay}s`,
        animationTimingFunction: petal.timing,
        transform: `rotate(${petal.rotate}deg)`
      }"
    ></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

// 花瓣配置
const petals = ref([]);
const petalCount = 10; // 花瓣数量
const colors = [
  '#FFD7E5', // 浅粉
  '#FFFACD', // 浅黄
  '#E0FFFF', // 浅蓝
  '#E6E6FA', // 淡紫
  '#F0E68C'  // 卡其
];

// 生成随机花瓣属性
function createPetals() {
  petals.value = [];
  for (let i = 0; i < petalCount; i++) {
    petals.value.push({
      left: Math.random() * 100, // 随机水平位置
      size: 10 + Math.random() * 20, // 随机大小
      color: colors[Math.floor(Math.random() * colors.length)], // 随机颜色
      duration: 10 + Math.random() * 20, // 随机下落时间
      delay: Math.random() * 10, // 随机延迟
      rotate: Math.random() * 360, // 随机旋转角度
      timing: `cubic-bezier(${0.2 + Math.random() * 0.6}, ${0.2 + Math.random() * 0.6}, ${0.2 + Math.random() * 0.6}, 1)`
    });
  }
}

onMounted(() => {
  createPetals();
  // 定时补充花瓣
  setInterval(createPetals, 15000);
});
</script>

<style scoped>
.petal-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none; /* 花瓣不影响鼠标交互 */
  z-index: 1; /* 确保在内容下方 */
  overflow: hidden;
}

.petal {
  position: absolute;
  border-radius: 100% 0; /* 花瓣形状 */
  opacity: 0.7;
  animation: fall linear infinite;
  will-change: transform, top, left; /* 优化动画性能 */
}

@keyframes fall {
  0% {
    transform: translateY(-10px) rotate(0deg);
    opacity: 0.7;
  }
  50% {
    opacity: 0.5;
  }
  100% {
    transform: translateY(100vh) rotate(360deg);
    opacity: 0.2;
  }
}
</style>