package ru.twinown.expertcourseunscramblegame

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import ru.twinown.expertcourseunscramblegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var uiState: GameUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GameViewModel

    private val textWatcher=object:TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)= Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =Unit
        override fun afterTextChanged(s: Editable?) {
            uiState = viewModel.handleUserInput(text = s.toString())
            uiState.update(binding = binding)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         viewModel = (application as UnscrApp).viewModel

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            uiState.update(binding = binding)
        }

        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            uiState.update(binding = binding)
        }



        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(KEY, GameUiState::class.java) as GameUiState
            } else {
                savedInstanceState.getSerializable(KEY) as GameUiState
            }
        }
        uiState.update(binding = binding)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onResume() {
        super.onResume()
        //листенер ставим, как ты понял, только когда пользователь уже что-то видит

        //значит, при  восстановлении после смерти активти(после чека,напр,у которого в поле ввода есть ошибка)
        // у тебя листенер уже был в онкриайте, а
        //текста в поле инпута еще не было( появлялся только на онРесторе,еоторый после онстарта)
        //потому перед показом ещё раз вычислялось sufficientstate ->ошибки никакой
        //теперь сделали по-другому,то есть слушаются изменения только от ЮЗЕРА,а не от системы

        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    companion object {
        private const val KEY = "uiState"
    }
}