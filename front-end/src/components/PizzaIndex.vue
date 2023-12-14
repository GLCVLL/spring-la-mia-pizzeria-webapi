<template>
    <div class="container mt-4">
        <!-- Barra di ricerca -->
        <input type="text" class="form-control mb-4" placeholder="Cerca pizza..." v-model="searchBar">

        <h1 class="mb-3">Lista Pizze</h1>
        <div class="row">
            <div class="col-md-4 mb-2" v-for="pizza in filteredPizzas" :key="pizza.id">
                <div class="card" @click="$emit('openPizza', pizza.id)">
                    <img :src="pizza.img" class="card-img-top" alt="Immagine della pizza">
                    <div class="card-body">
                        <p class="card-title">{{ pizza.name }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
// IMPORT LIBS
import { defineProps, ref, computed } from 'vue';

// EMITS
const emits = defineEmits(["openPizza"]);

// PROPS
const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});

// Data
const searchBar = ref('');

// Computed property per filtrare le pizze
const filteredPizzas = computed(() => {
    return props.pizzas.filter(pizza =>
        pizza.name.toLowerCase().includes(searchBar.value.toLowerCase())
    );
});
</script>

<style scoped>
.list-elem {
    cursor: pointer;
    list-style-type: none;
}

.card-img-top {
    width: 100%;
    height: auto;
    object-fit: cover;
}
</style>