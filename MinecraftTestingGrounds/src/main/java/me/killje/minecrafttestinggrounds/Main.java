package me.killje.minecrafttestinggrounds;

import java.util.ArrayList;
import net.minecraft.server.v1_7_R3.DamageSource;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

/**
 *
 * @author Patrick Beuks (killje)
 */
public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onFireWorksLaunceEvent(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getItem() != null && event.getItem().getType() == Material.FIREWORK) {
                Location shootLocation = event.getPlayer().getLocation();
                Vector directionVector = shootLocation.getDirection().normalize();
                double startShift = 2;
                Vector shootShiftVector = new Vector(directionVector.getX() * startShift, directionVector.getY() * startShift + 1.5, directionVector.getZ() * startShift);
                shootLocation = shootLocation.add(shootShiftVector.getX(), shootShiftVector.getY(), shootShiftVector.getZ());

                Fireball fireball = shootLocation.getWorld().spawn(shootLocation, Fireball.class);
                fireball.setVelocity(directionVector.multiply(2.0));
                fireball.setIsIncendiary(false);
                fireball.setShooter(event.getPlayer());
                fireball.setYield(0);

                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (!e.getEntity().getType().equals(EntityType.FIREBALL)) {
            return;
        }
        if (((Fireball) e.getEntity()).getShooter() instanceof Player) {
            //DO STUFF
        }
    }
}
