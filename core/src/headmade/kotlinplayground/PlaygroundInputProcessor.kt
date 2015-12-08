package headmade.kotlinplayground

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector3

class PlaygroundInputProcessor(private val screen: KotlinPlayground) : InputProcessor {

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Keys.LEFT || keycode == Keys.A) {
            screen.cam.translate(-1f, 0f)
            screen.cam.update()
            return true
        } else if (keycode == Keys.RIGHT || keycode == Keys.D) {
            screen.cam.translate(1f, 0f)
            screen.cam.update()
            return true
        } else if (keycode == Keys.UP || keycode == Keys.W) {
            screen.cam.translate(0f, 1f)
            screen.cam.update()
            return true
        } else if (keycode == Keys.DOWN || keycode == Keys.S) {
            screen.cam.translate(0f, -1f)
            screen.cam.update()
            return true
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val mouse = screen.cam.unproject(Vector3(screenX.toFloat(), screenY.toFloat(), 0f))
        val force = mouse
        screen.setForce(force)
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val mouse = screen.cam.unproject(Vector3(screenX.toFloat(), screenY.toFloat(), 0f))
        val force = mouse
        screen.setForce(force)
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val mouse = screen.cam.unproject(Vector3(screenX.toFloat(), screenY.toFloat(), 0f))
        val force = mouse
        screen.setForce(force)
        return true
    }

    override fun scrolled(amount: Int): Boolean {
        screen.cam.zoom += amount * 0.5f
        screen.cam.zoom = MathUtils.clamp(screen.cam.zoom, 0.5f, 50f)
        screen.cam.update()
        Gdx.app.log(TAG, "new zoom " + screen.cam.zoom)
        return false
    }

    companion object {
        private val TAG = PlaygroundInputProcessor::class.java.name
    }

}
