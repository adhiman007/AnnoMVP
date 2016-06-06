# AnnoMVP

AnnoMVP provides you a fancy way to create MVP architecture for your application.

No need to create multiple ``interface`` for your ``Model``, ``View`` & ``Presenter``. AnnoMVP have your back, it will take the headache and create all those for your without any effort.

## Using AnnoMVP
AnnoMVP is an annotation based library that is very easy to use in your daily development life. The only thing that you need to know is ``@MVP``, ``@Presenter`` & ``@View``.

Add the ``mvp.jar`` file in your ``libs`` folder and include the following code to ``build.gradle`` file:

- inside app: ``classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'``

- inside application: ``apply plugin: 'com.neenbedankt.android-apt'``

### ``@MVP``

In-order to segregate your code MVP ask you to create different modules for everything.
- Model
- View
- Presenter

#### Model
Model is where you keep all your business logic's, whether it's WebService consumption, Database queries or any calculation suitable for your application.

#### View
View is what your user will see and interact with.

#### Presenter
Presenter will be the intermediater that will communicate with **Model** to get the work done and updates the **View** accordingly.

Let us take an example where a developer is having a ``class`` ``LoginActivity`` and want to create all the above using AnnoMVP, so just follow these steps:

- Create an ``interface`` and name it ``Login`` and annotate it with ``@MVP``.
- Add all the methods that corresponds to your business logic like: ``public void login(String email, String password);`` and annotate it with ``@Presenter``
- Add all the methods that corresponds to your view's like: ``public void onUserLogin();`` and annotate it with ``@View``.

> Here ``@MVP`` will create an ``interface`` 

1. ``LoginModel`` with the method ``public void login(String email, String password);``
2. ``LoginView`` with the method ``public void onUserLogin();``
3. ``LoginInteractor`` with the method ``public void login(String email, String password, OnLoginInteractedListener listener);``
4. ``OnLoginInteractedListener`` with the method ``public void onUserLogin();``

> The only thing that developer need to do is to manage the mapping of the above created ``interface``:

1. Create a ``class`` with name ``LoginPresenterImp`` and ``implement`` it with ``LoginPresenter`` and implement the unimplemented methods in it.
2. Create a ``class`` with name ``LoginInteractorImp`` and ``implement`` it with ``LoginInteractor`` and implement the unimplemented methods in it.

### Example Code
```
@MVP
public interface Login {
    @Presenter
    void login(String email, String password);

    @View
    void onUserLogin();
}
```

```
public class LoginPresenterImpl implements LoginPresenter, OnLoginInteractedListener {
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void login(String email, String password) {
        interactor.login(email, password, this);
    }

    @Override
    public void onUserLogin() {
        view.onUserLogin();
    }
}
```

```
public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(String email, String password, final OnLoginInteractedListener listener) {
        // TODO login process
        // TODO after login process
        listener.onUserLogin();
    }
}
```

```
LoginPresenter presenter = new LoginPresenterImp(this);
presenter.login("abc@example.com", "123456");
```

### Version
1.0
