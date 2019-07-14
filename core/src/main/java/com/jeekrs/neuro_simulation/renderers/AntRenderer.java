package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.livings.AntFighter;
import com.jeekrs.neuro_simulation.entities.livings.AntWorker;
import com.jeekrs.neuro_simulation.interfaces.Alive;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class AntRenderer extends Renderer {
    private Texture antFighterTexture = new Texture(Gdx.files.internal("ant64.png"));
    private Sprite antFighterSprite = new Sprite(antFighterTexture);
    private Texture antWorkerTexture = new Texture(Gdx.files.internal("ant2.png"));
    private Sprite antWorkerSprite = new Sprite(antWorkerTexture);
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer lineRenderer = new ShapeRenderer();
    private ShapeRenderer solidRenderer = new ShapeRenderer();

    private void drawLivings() {
        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        int count = 0;
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof AntFighter) {
                AntFighter lv = (AntFighter) e;
                antFighterSprite.setRotation(lv.getDirection());
                antFighterSprite.setPosition(lv.getPos().x - lv.getRadius(), lv.getPos().y - lv.getRadius());
                antFighterSprite.draw(batch);
                count += 1;
            } else if (e instanceof AntWorker) {
                AntWorker lv = (AntWorker) e;
                antWorkerSprite.setRotation(lv.getDirection());
                antWorkerSprite.setPosition(lv.getPos().x - lv.getRadius(), lv.getPos().y - lv.getRadius());
                antWorkerSprite.draw(batch);
                count += 1;
            }
            if (count > 20) {
                batch.end();
                batch.begin();
                count = 0;
            }
        }
        batch.end();

    }

    @Override
    public void render() {
        drawLivings();
        drawLives();
    }

    private void drawLives() {
        lineRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        lineRenderer.setColor(0, 0, 0, 1);
        lineRenderer.begin(Line);

        solidRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        solidRenderer.begin(Filled);
        int count = 0;
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof Alive) {
                Alive lv = (Alive) e;
                if (lv.getAgenda() == systemManager.agendaSystem.playerAgenda)
                    solidRenderer.setColor(0, 0.8f, 0, 1);
                else
                    solidRenderer.setColor(0.8f, 0, 0, 1);
                solidRenderer.rect(e.getPos().x - 30, e.getPos().y + 50, 60 * lv.getHealth() / lv.getHealthLimit(), 15);
                lineRenderer.rect(e.getPos().x - 30, e.getPos().y + 50, 60, 15);

                count += 1;
                if (count > 20) {
                    lineRenderer.end();
                    lineRenderer.begin(Line);
                    count = 0;
                }
            }
        }
        lineRenderer.end();
        solidRenderer.end();
    }

    @Override
    public void dispose() {
        antFighterTexture.dispose();
        batch.dispose();
    }
}