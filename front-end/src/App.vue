<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light d-flex align-item-center justify-content-center">
    <div>
      <img src="./assets/pizzaLogo.jpg" alt="Logo">
    </div>
  </nav>
  <div class="container mt-4">
    <pizza-form v-if="creatingPizza" @back="creatingPizza = false" @created="pizzaCreated" />
    <div v-else>
      <pizza-index v-if="pizzas && pizzaActive == null" :pizzas="pizzas" @open-pizza="openPizza" />
      <pizza-show v-else-if="pizzaActive" :pizza="pizzaActive" @close-pizza="closePizza" @delete-pizza="deletePizza" />
    </div>
    <div v-if="!creatingPizza && pizzaActive == null">
      <button class="btn btn-primary mb-3" @click="creatingPizza = true">
        Crea una nuova pizza
      </button>
    </div>
  </div>
</template>

<script setup>
// IMPORT LIBS
import { onMounted, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PizzaIndex from './components/PizzaIndex.vue';
import PizzaShow from './components/PIzzaShow.vue';
import PizzaForm from './components/PizzaForm.vue';

// DATA
const pizzas = ref(null);
const pizzaActive = ref(null);
const creatingPizza = ref(false);

// FUNCTIONS
const openPizza = (id) => {
  pizzas.value.forEach((pizza) => {
    if (pizza.id === id) {
      pizzaActive.value = pizza;
    }
  });
};
const closePizza = (update) => {
  pizzaActive.value = null;
  if (update) updatePizzas();
};
const pizzaCreated = () => {
  creatingPizza.value = false;
  updatePizzas();
};
const updatePizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/pizzas");
  pizzas.value = data.data;
};
const deletePizza = () => {
  pizzaActive.value = null;
  updatePizzas();
};

// HOOKS
onMounted(updatePizzas);
</script>

<style scoped>
img {
  max-width: 100px;
  height: auto;
}
</style>