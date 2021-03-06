package com.jeekrs.neuro_simulation.displays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.displays.renderers.*;
import com.jeekrs.neuro_simulation.game.SimpleSystem;

import java.util.ArrayList;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;


public class RenderSystem extends SimpleSystem {
    public ArrayList<Renderer> renderers = new ArrayList<>();
    public OrthographicCamera camera = new OrthographicCamera();
    public Viewport viewport = new ExtendViewport(600, 800, camera);

    public RenderSystem() {
        addRenderer(new NestRenderer());
        addRenderer(new FoodRenderer());
        addRenderer(new LivingRenderer());
    }

    public ViewController viewController = new ViewController(this);


    @Override
    public void init() {

        systemManager.inputSystem.inputStack.stack.addFirst(viewController);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }


    public boolean inView(Position pos) {
        Vector2 view = viewport.project(pos.toVector2());
        return !(view.x < -10) && !(view.x > Gdx.graphics.getWidth() + 10) && !(view.y < -10) && !(view.y > Gdx.graphics.getHeight() + 10);
    }

    @Override
    public void update(float delta) {
        render(delta);
        viewController.update(delta);

    }

    public void render(float delta) {
        camera.update();

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        for (Renderer renderer : renderers) {
            renderer.render();
        }
    }

    @Override
    public void dispose() {
        renderers.forEach(Renderer::dispose);
    }

}
