package com.fungineers.zombie;

public interface Screen {
    /** Called when the screen should update itself, e.g. continue a simulation etc.
     * 
     * @param app the Application */

    public void render (float delta);

    /** Called by GdxInvaders to check whether the screen is done.
     * 
     * @return true when the screen is done, false otherwise */
    public boolean isDone ();

    /** Cleans up all resources of the screen, e.g. meshes, textures etc. */
    public Screen dispose ();
    
    public Screen dispose (ZombieManager zombieManager);
}