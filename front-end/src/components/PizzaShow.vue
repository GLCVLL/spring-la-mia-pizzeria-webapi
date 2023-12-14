<template>
    <div v-if="!pU" class="container mt-4">
        <div class="card">
            <img :src="p.img" class="card-img-top" alt="Immagine della pizza">
            <div class="card-body">
                <h5 class="card-title">
                    Pizza: {{ p.name }}
                </h5>
                <p class="card-text">Prezzo: {{ p.price }}€</p>
                <p class="card-text">{{ p.description }}</p>
                <button class="btn btn-warning me-2" @click="pU = true">Modifica</button>
                <button class="btn btn-danger ml-2" @click="deletePizza(p.id)">Elimina</button>
            </div>
        </div>
        <a class="btn btn-primary my-3" @click="$emit('closePizza', p != pizza)">Torna alla lista</a>
    </div>
    <pizza-form v-else :oldPizza="pizza" @back="pU = false" @created="updatedPizza" />
</template>

<script setup>
// IMPORT LIBS
import { ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PizzaForm from './PizzaForm.vue';

// PROPS
const props = defineProps({
    pizza: {
        type: Object,
        required: true
    }
});

// DATA
const pU = ref(false);
const p = ref(props.pizza);

// EMITS
const emits = defineEmits(["closePizza", "deletePizza"]);

// FUNCTIONS
const deletePizza = async (id) => {
    const data = await axios.delete(
        `http://localhost:8080/api/pizzas/${id}`
    );
    console.log("data", data);

    emits("deletePizza");
}
const updatedPizza = (newPizza) => {
    pU.value = false;
    p.value = newPizza;
}
</script>

<style scoped>
.link {
    cursor: pointer;
    text-decoration: underline;
}
</style>