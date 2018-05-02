package com.marquezr.recipeapp.controllers;

import com.marquezr.recipeapp.domain.Recipe;
import com.marquezr.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito.*;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class indexControllerTest {
    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    indexController controller;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new indexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {

        // given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String viewName = controller.getIndexPage(model);
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model,times(1)).addAttribute(ArgumentMatchers.eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());


    }
}