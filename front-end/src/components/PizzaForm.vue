<template>
    <form @submit.prevent="submit" class="container mt-4">
        <div class="row mb-3">
            <label for="name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="name" id="name" v-model="newPizza.name">
            </div>
        </div>

        <div class="row mb-3">
            <label for="price" class="col-sm-2 col-form-label">Price</label>
            <div class="col-sm-10">
                <input type="number" class="form-control" name="price" id="price" v-model="newPizza.price">
            </div>
        </div>

        <div class="row mb-3">
            <label for="description" class="col-sm-2 col-form-label">Text</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="description" id="description" v-model="newPizza.description">
            </div>
        </div>

        <div class="row mb-3">
            <label for="img" class="col-sm-2 col-form-label">Image</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="img" id="img" v-model="newPizza.img">
            </div>
        </div>

        <div class="row">
            <div class="col-sm-10 offset-sm-2">
                <button type="submit" class="btn btn-primary me-2">
                    {{ props.oldPizza == null ? "Create" : "Update" }}
                </button>
                <button type="button" class="btn btn-secondary" @click="$emit('back')">
                    Back
                </button>
            </div>
        </div>
    </form>
</template>

<script setup>
// IMPORT LIBS
import { defineEmits, ref } from 'vue';
import axios from 'axios';

// PROPS
const props = defineProps({
    oldPizza: {
        type: Object,
        required: false,
        default: null
    }
});

// DATA
const newPizza = ref({
    name: "",
    price: null,
    description: "",
    img: ""
});
if (props.oldPizza != null) {

    newPizza.value = {
        name: props.oldPizza.name,
        price: props.oldPizza.price,
        description: props.oldPizza.description,
        img: props.oldPizza.img
    };
}

// EMITS
const emits = defineEmits(["back", "created"]);

// FUNCTIONS
const submit = async () => {
    if (props.oldPizza == null) {

        const data = await axios.post(
            "http://localhost:8080/api/pizzas",
            newPizza.value
        );

        console.log("data", data);

        emits("created");

        return;
    }

    const data = await axios.put(
        `http://localhost:8080/api/pizzas/${props.oldPizza.id}`,
        newPizza.value
    );

    console.log("data", data);

    emits("created", newPizza.value);
}


</script>