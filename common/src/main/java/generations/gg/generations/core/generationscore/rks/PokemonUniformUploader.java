/*
 * Copyright (C) 2023 ThePokeCraftMod
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package generations.gg.generations.core.generationscore.rks;

import com.thepokecraftmod.rks.storage.AnimatedObjectInstance;
import org.joml.Vector3f;

import java.util.List;

public class PokemonUniformUploader {

    private final MaterialUploader uploader;
    private AnimatedObjectInstance instance;

    public PokemonUniformUploader(MaterialUploader uploader) {
        this.uploader = uploader;
    }

    public void upload(String materialName) {
        var shader = uploader.materials.get(materialName).shader;
        var color = 1;

        shader.uploadVec3fs(
                "lightColors",
                new Vector3f(color, color, color),
                new Vector3f(color, color, color)
        );

        var distance = 400;
        var worldSpacePositions = List.of(
                new Vector3f(-distance, distance, distance),
                new Vector3f(distance, distance, distance)
        ).toArray(Vector3f[]::new);

        shader.uploadVec3fs(
                "lightPositions",
                worldSpacePositions
        );
        uploader.handle(materialName);
    }

    public void setInstance(AnimatedObjectInstance newInstance) {
        this.instance = newInstance;
    }
}