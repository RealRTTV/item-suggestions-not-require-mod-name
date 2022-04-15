package ca.rttv.isnrmn.mixin;

import net.minecraft.command.CommandSource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;
import java.util.function.Function;

@Mixin(CommandSource.class)
public interface CommandSourceMixin {
    @Shadow
    static boolean method_27136(String string, String string2) {return false;}

    /**
     * @reason cuz im lazy :p, also no-one uses this method so yay
     * @author RTTV
     */
    @Overwrite
    static <T> void forEachMatching(Iterable<T> candidates, String string, Function<T, Identifier> identifier, Consumer<T> action) {
        boolean bl = string.indexOf(58) > -1;

        for(T object : candidates) {
            Identifier identifier2 = identifier.apply(object);
            if (bl) {
                String string2 = identifier2.toString();
                if (method_27136(string, string2)) {
                    action.accept(object);
                }
            } else if (method_27136(string, identifier2.getNamespace())
                    || method_27136(string, identifier2.getPath())) {
                action.accept(object);
            }
        }

    }
}
