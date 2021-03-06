package org.statix.bukkit.command;

import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.statix.bukkit.command.annotation.CommandCooldown;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Это наиприкраснейший класс, с помощью которого можно создавать
 * большие команды без повторяющегося кода по типу if (args[0] == "gui") или switch(args[0])
 */
public abstract class BaseMegaCommand<S extends CommandSender>
        extends BaseCommand<S> {

    private final Map<String, Method> commandArguments = new HashMap<>();


// ================================================================================================================ //

    protected final int minimalArgsCount;

    @Setter
    protected Consumer<S> noArgumentMessage;

// ================================================================================================================ //

    /**
     * На тот случай, если у команды несколько
     * вариаций алиасов
     *
     * @param command - главный алиас
     * @param aliases - алиасы
     */
    public BaseMegaCommand(String command, String... aliases) {
        this(0, command, aliases);
    }

    /**
     * На тот случай, если у команды несколько
     * вариаций алиасов
     *
     * @param minimalArgsCount - минимальное количество аргументов для #onUsage
     * @param command          - главный алиас
     * @param aliases          - алиасы
     */
    public BaseMegaCommand(int minimalArgsCount, String command, String... aliases) {
        super(command, aliases);

        this.minimalArgsCount = minimalArgsCount;

        Arrays.stream(getClass().getDeclaredMethods())
                .filter(method -> method.getDeclaredAnnotation(CommandArgument.class) != null)
                .filter(method -> method.getParameterCount() == 2 && (Arrays.equals(method.getParameterTypes(), new Class<?>[]{(Class<S>) ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0], String[].class})))

                .forEach(method -> {
                    method.setAccessible(true);

                    commandArguments.put(method.getName().toLowerCase(Locale.ROOT), method);

                    for (String alias : method.getDeclaredAnnotation(CommandArgument.class).aliases()) {
                        commandArguments.put(alias.toLowerCase(Locale.ROOT), method);
                    }
                });

        setNoArgumentMessage(this::onUsage);
    }

    @SneakyThrows
    @Override
    public void onExecute(S commandSender, String[] args) {
        if (args.length == minimalArgsCount) {
            onUsage(commandSender);
            return;
        }

        Method argumentMethod = commandArguments.get(args[minimalArgsCount].toLowerCase());

        if (argumentMethod != null) {
            argumentMethod.invoke(this, commandSender, Arrays.copyOfRange(args, minimalArgsCount + 1, args.length));
        } else {
            noArgumentMessage.accept(commandSender);
        }
    }

    /**
     * Использование основной команды без
     * аргументов
     *
     * @param commandSender - отправитель
     */
    protected abstract void onUsage(S commandSender);

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CommandArgument {

        String[] aliases() default {};
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AliasPermission {

        String permission();
        String message() default "§cОшибка, у Вас недостаточно прав!";
    }
    //@Retention(RetentionPolicy.RUNTIME)
    //@Target(ElementType.METHOD)
    //public @interface AliasCooldown {
    //    long cooldownMillis();
    //    CommandCooldown.ReceiverModifier receiverModifier() default CommandCooldown.ReceiverModifier.ONLY_SENDER;
    //
    //    enum ReceiverModifier {
    //
    //        ONLY_SENDER,
    //        PUBLIC;
    //    }
    //}
}
