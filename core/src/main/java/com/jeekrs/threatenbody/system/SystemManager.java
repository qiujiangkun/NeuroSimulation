package com.jeekrs.threatenbody.system;

import java.util.ArrayList;

public class SystemManager {
    public ArrayList<SimpleSystem> systems = new ArrayList<>();
    public PhysicsSystem physicsSystem = new PhysicsSystem();
    public RenderSystem renderSystem = new RenderSystem();
    public InputSystem inputSystem = new InputSystem();
    public WorldSystem worldSystem;
    public UISystem UISystem = new UISystem();

    public SystemManager() {

    }


    public void init() {
        this.addSystem(inputSystem);
        this.addSystem(physicsSystem);
        this.addSystem(renderSystem);
        this.addSystem(UISystem);

        systems.forEach(SimpleSystem::init);
    }

    public void update(float delta) {
        systems.forEach(e -> e.update(delta));
    }

    public void addSystem(SimpleSystem system) {
        systems.add(system);
        system.setSystemManager(this);
    }

    public void resize(int width, int height) {
        systems.forEach(e -> e.resize(width, height));
    }
    public void setWorldSystem(WorldSystem worldSystem) {
        this.worldSystem = worldSystem;
    }

    public void dispose() {
        systems.forEach(SimpleSystem::dispose);
    }
}